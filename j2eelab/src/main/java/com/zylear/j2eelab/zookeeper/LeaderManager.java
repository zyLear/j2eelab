package com.zylear.j2eelab.zookeeper;

import com.zylear.j2eelab.zookeeper.ZookeeperCuratorClient.Path;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xiezongyu on 2018/8/21.
 */
public class LeaderManager {


    private final static Logger logger = LoggerFactory.getLogger(LeaderManager.class);
    private final ZookeeperCuratorClient client;
    private final Collection<Leader> leaders;

    public LeaderManager(ZookeeperCuratorClient client, Collection<Leader> leaders) {
        this.client = client;
        this.leaders = leaders;
    }

    private List<LeaderSelector> selectors;

    public void start() {
        logger.info("get leader class {}", leaders.size());
        selectors = new ArrayList<>(leaders.size());
        String parentLeaderPath = client.getLeaderParentPath();
        if (StringUtils.isEmpty(parentLeaderPath)) {
            // 兼容老代码
            logger.warn("no leader parent path declared, using lock path:{}", client.getLockParentPath());
            parentLeaderPath = client.getLockParentPath();
        }
        for (Leader leader : this.leaders) {
            String path = Path.combine(parentLeaderPath, leader.getResourcePath());
            LeaderSelector leaderSelector = new LeaderSelector(client.getClient(), path, new LeadImp(leader));
            leaderSelector.autoRequeue();
            leaderSelector.start();
            selectors.add(leaderSelector);
        }
    }

    public void stop() {
        for (LeaderSelector s : selectors) {
            try {
                s.close();
            } catch (Exception e) {
                logger.error("select close error", e);
            }

        }
    }

    private static class LeadImp extends LeaderSelectorListenerAdapter {
        private Leader leader;

        public LeadImp(Leader leader) {
            this.leader = leader;
        }

        @Override
        public void takeLeadership(CuratorFramework client) throws Exception {
            this.leader.takeLeadership(client);
        }

        @Override
        public void stateChanged(CuratorFramework client, ConnectionState newState) {
            this.leader.stateChanged(client, newState);
            super.stateChanged(client, newState);
        }

    }


    public static abstract class Leader {
        private Logger logger = LoggerFactory.getLogger(Leader.class);

        private boolean isLeaderExecutable = true;

        public int getMinIntervalMS() {
            return 60000;
        }

        public abstract String getResourcePath();

        public void stateChanged(CuratorFramework client, ConnectionState newState) {
            logger.info("{}. leader status to " + newState, this.getClass().getCanonicalName());
            if (newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED) {
                this.success();
            } else {
                this.broke();
            }
        }

        public void takeLeadership(CuratorFramework arg0) {
            while (isLeaderExecutable) {
                Long start = System.currentTimeMillis();
                try {
                    this.execute();
                } catch (Exception e) {
                    logger.error(String.format("path %s execute error.", this.getResourcePath()), e);
                }
                Long end = System.currentTimeMillis();

                long time = end - start;
                if (this.getMinIntervalMS() > time) {
                    try {
                        Thread.sleep(this.getMinIntervalMS() - time);
                    } catch (InterruptedException e) {
                        logger.warn("sleep is error", e);
                    }
                }
            }
            this.endLeader();
        }


        public boolean isLeaderExecutable() {
            return isLeaderExecutable;
        }

        public void setLeaderExecutable(boolean leaderExecutable) {
            isLeaderExecutable = leaderExecutable;
        }

        public abstract void execute();

        public void endLeader() {
        }

        /**
         * 只有变化的是有才调用
         */
        public void broke() {
            isLeaderExecutable = false;
            logger.error("{} leader status detached! ", this.getClass().getCanonicalName());
        }

        /**
         * 释放Leader
         */
        public void releaseLeaderShip() {
            isLeaderExecutable = false;
            logger.info("{} leader status detached! ", this.getClass().getCanonicalName());
        }

        /**
         * 只有变化的是有才调用
         */
        public void success() {
            isLeaderExecutable = true;
            logger.info("{} granted leader!", this.getClass().getCanonicalName());
        }

    }

}
