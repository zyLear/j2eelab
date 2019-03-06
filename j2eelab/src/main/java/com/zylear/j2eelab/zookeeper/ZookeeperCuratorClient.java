package com.zylear.j2eelab.zookeeper;

import com.zylear.j2eelab.zookeeper.LeaderManager.Leader;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NotEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiezongyu on 2018/8/21.
 */
public class ZookeeperCuratorClient {

    Logger logger = LoggerFactory.getLogger(ZookeeperCuratorClient.class);

    private CuratorFramework curator;

    private String zkConnectString;

    private String lockParentPath;

    private String leaderParentPath;

    private LeaderManager leaderManager;


    public static void main(String[] args) {

        ZookeeperCuratorClient zookeeperCuratorClient = new ZookeeperCuratorClient();
        zookeeperCuratorClient.setZkConnectString("127.0.0.1:2181");
        zookeeperCuratorClient.setLockParentPath("/rootNode");
        zookeeperCuratorClient.start();
        while (true) {
            try {
                Thread.sleep(60 * 60 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public <T> T lock(Mutex<T> mutex) {
        String path = Path.combine(this.getLockParentPath(), mutex.getResoursePath());
        InterProcessMutex lock = new InterProcessMutex(this.getClient(), path);
        boolean success = false;
        try {
            try {
                success = lock.acquire(mutex.getTimeout(), mutex.getTimeUnit());
            } catch (Exception e) {
                throw new RuntimeException(String.format("resource path %s lock error.", path), e);
            }
            if (success) {
                return (T) mutex.execute();
            } else {
                return null;
            }
        } finally {
            try {
                if (success) {
                    lock.release();
                }
            } catch (Exception e) {
                logger.error("release lock failed when path is:{}", path, e);
            }
            try {
                if (this.curator.getChildren().forPath(path).isEmpty()) {
                    getClient().delete().guaranteed().forPath(path);
                }
            } catch (NoNodeException e) {
                logger.debug("delete path error for no node. path is:{}, ignore it", path, e);
            } catch (NotEmptyException e) {
                logger.warn("delete path error for not empty. path is:{}, ignore it", path, e);
            } catch (Exception e) {
                logger.error("delete path error. path is:" + path, e);
            }
        }
    }

    public CuratorFramework getClient() {
        return curator;
    }


    public String getZkConnectString() {
        return zkConnectString;
    }

    public void setZkConnectString(String zkConnectString) {
        this.zkConnectString = zkConnectString;
    }

    public String getLockParentPath() {
        return lockParentPath;
    }

    public void setLockParentPath(String lockParentPath) {
        this.lockParentPath = lockParentPath;
    }

    public String getLeaderParentPath() {
        return leaderParentPath;
    }

    public void setLeaderParentPath(String leaderParentPath) {
        this.leaderParentPath = leaderParentPath;
    }

    public static abstract class Mutex<T> {
        public abstract String getResoursePath();

        public abstract T execute();

        public int getTimeout() {
            return 10;
        }

        public TimeUnit getTimeUnit() {
            return TimeUnit.SECONDS;
        }
    }


    public static class Path {
        private final static String PATH_SPLIT = "/";



	/*
     * get directory path
	 *
	 * @param path file or directory path
	 *
	 * @return directory path
	 */

        public static String getDirectoryPath(String path) {
            File file = new File(path);
            return file.getAbsolutePath();
        }

        public static String combine(String path1, String path2) {
            if (StringUtils.isEmpty(path1) && StringUtils.isEmpty(path2))
                throw new RuntimeException("path1 or path2 must be non-empty.");
            else if (StringUtils.isEmpty(path1)) {
                return path2;
            } else if (StringUtils.isEmpty(path2)) {
                return path1;
            } else if (path1.endsWith(PATH_SPLIT) && path2.startsWith(PATH_SPLIT)) {
                return path1 + path2.substring(1);
            } else if (path1.endsWith(PATH_SPLIT) || path2.startsWith(PATH_SPLIT)) {
                return path1 + path2;
            } else {
                return path1 + PATH_SPLIT + path2;
            }
        }

    }

    public void start() {
        logger.info("mutex Invoker Proxy init.");
        this.curator = CuratorFrameworkFactory.builder()
                .connectString(this.zkConnectString)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        this.curator.start();
//
        logger.info("mutex Invoker Proxy inited.");
    }

    public void start(List<Leader> leaders) {
        logger.info("mutex Invoker Proxy init.");
        this.curator = CuratorFrameworkFactory.builder()
                .connectString(this.zkConnectString)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        this.curator.start();
//        if( applicationContext != null ) {
//        List<Leader> leaders = ;
        if (leaders != null && leaders.size() > 0) {
            this.leaderManager = new LeaderManager(this, leaders);
            this.leaderManager.start();
//            }
        }
        logger.info("mutex Invoker Proxy inited.");
    }


}


