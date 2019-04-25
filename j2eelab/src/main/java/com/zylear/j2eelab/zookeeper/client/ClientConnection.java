package com.zylear.j2eelab.zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by xiezongyu on 2019/4/25.
 */
public class ClientConnection {

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new MyWatcher());
        zooKeeper.

        System.out.println(zooKeeper.getState());
        while (true) {
            Thread.sleep(5000);
        }

    }

    public static class MyWatcher implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            if(watchedEvent.getState().equals(Event.KeeperState.SyncConnected)) {
                doBus();
            }
            System.out.println("接收内容："+watchedEvent.toString());
        }

        private void doBus() {
            System.out.println("做业务！");
        }
    }

}
