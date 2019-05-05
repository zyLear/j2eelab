package com.zylear.j2eelab;

import com.zylear.j2eelab.zookeeper.LeaderManager.Leader;
import com.zylear.j2eelab.zookeeper.ZookeeperCuratorClient;
import com.zylear.j2eelab.zookeeper.ZookeeperCuratorClient.Mutex;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiezongyu on 2018/8/21.
 */
public class ZookeeperTest {

    @Test
    public void test() {
        tt("/rootNode");
    }

    @Test
    public void test1() {
        tt("/rootNode");
    }

    @Test
    public void test2() {
        ttt(new Leader() {
            @Override
            public String getResourcePath() {
                return "/leader/test/v2";
            }

            @Override
            public void execute() {
                System.out.println("leader1 start");
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("leader1 end");
            }
        });
    }

    @Test
    public void test3() {
        ttt(new Leader() {
            @Override
            public String getResourcePath() {
                return "/leader/v2";
            }

            @Override
            public void execute() {
                System.out.println("leader2 start");
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("leader2 end");
            }
        });
    }


    public void ttt(Leader leader) {
        ZookeeperCuratorClient zookeeperCuratorClient = new ZookeeperCuratorClient();
        zookeeperCuratorClient.setZkConnectString("127.0.0.1:2181");
        zookeeperCuratorClient.setLockParentPath("/rootNode");
        zookeeperCuratorClient.setLeaderParentPath("/leaderp/lalala/hao");
        zookeeperCuratorClient.start(Arrays.asList(leader));
        try {
            zookeeperCuratorClient
                    .getClient()
                    .create()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .withACL(Ids.OPEN_ACL_UNSAFE)
                    .inBackground((curatorframework, curatorevent) -> {
                                System.out.println(curatorevent);
                                System.out.println("sdf");
                            }
                    )
                    .forPath("/xzytest/xxx/aa/");
        } catch (Exception e) {
            e.printStackTrace();
        }


        while (true) {
            try {
                Thread.sleep(60 * 60 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void tt(String stirng) {
        ZookeeperCuratorClient zookeeperCuratorClient = new ZookeeperCuratorClient();
        zookeeperCuratorClient.setZkConnectString("127.0.0.1:2181");
        zookeeperCuratorClient.setLockParentPath(stirng);
        zookeeperCuratorClient.start();


        zookeeperCuratorClient.lock(new Mutex<Boolean>() {

            @Override
            public String getResoursePath() {
                return "/one";
            }

            @Override
            public Boolean execute() {
                System.out.println("lock start");
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock end");
                return true;
            }

            @Override
            public TimeUnit getTimeUnit() {
                return TimeUnit.HOURS;
            }
        });

        while (true) {
            try {
                Thread.sleep(60 * 60 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
