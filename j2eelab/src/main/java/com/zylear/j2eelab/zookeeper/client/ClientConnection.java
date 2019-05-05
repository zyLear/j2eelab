package com.zylear.j2eelab.zookeeper.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiezongyu on 2019/4/25.
 */
public class ClientConnection {

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new MyWatcher());


        zooKeeper.create("/xzy/29/a", "something".getBytes(), Arrays.asList(new ACL(1, new Id())), CreateMode.PERSISTENT);


        System.out.println(zooKeeper.getState());
        while (true) {
            Thread.sleep(5000);
            System.out.println(zooKeeper.exists("xzy/29", false));
        }

    }

    public static class MyWatcher implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getState().equals(Event.KeeperState.SyncConnected)) {
                doBus();
            }
            System.out.println("接收内容：" + watchedEvent.toString());
        }

        private void doBus() {
            System.out.println("做业务！");
        }
    }

    @Test
    public void create() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new MyWatcher());


        zooKeeper.create("/xzy/29/a", "something".getBytes(), Arrays.asList(new ACL()), CreateMode.PERSISTENT);
        Thread.sleep(5000);
    }

    @Test
    public void vote() throws Exception {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState());
            }
        });
        //zk启动后试着进行选举
        selection(zk);

        TimeUnit.SECONDS.sleep(30); //阻塞住
        zk.close();
    }

    @Test
    public void vote2() throws Exception {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, (event) ->
                System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState())
        );
        //zk启动后试着进行选举
        selection(zk);

        TimeUnit.SECONDS.sleep(30); //阻塞住
        zk.close();
    }

    @Test
    public void unfair() throws Exception {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, event ->
                System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState()));

//        Stat exists = zk.exists("/serverUnfair", false);
//        if (exists.)

        String leaderPath = "/serverUnfair/leader";

        //1、创建/server（这个通过zkCli创建好了），注意这里是EPHEMERAL_SEQUENTIAL的
        //2、和非公平模式不一样，只需要创建一次节点就可以了
        String nodeVal = zk.create(leaderPath, "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        //System.out.println(nodeVal);

        //启动后试着进行选举
        selectionFair("/serverUnfair", zk, nodeVal);

        TimeUnit.SECONDS.sleep(30); //阻塞住
        zk.close();
    }


    private static void selection(final ZooKeeper zk) throws Exception {
        try {
            //1、创建/server（这个通过zkCli创建好了），参数3表示公有节点，谁都可以改
            zk.create("/server/leader", "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            //2、没有抛异常，表示创建节点成功了
            System.out.println("选举成功");
        } catch (KeeperException.NodeExistsException e) {
            System.out.println("选举失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3、监听节点删除事件，如果删除了，重新进行选举
            zk.getData("/server/leader", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState());
                    try {
                        if (Objects.equals(event.getType(), Event.EventType.NodeDeleted)) {
                            selection(zk);
                        }
                    } catch (Exception e) {
                    }
                }
            }, null);
        }
    }


    private static void selectionFair(final String path, final ZooKeeper zk, final String nodeVal) throws Exception {
        //2、遍历/server下的子节点，看看自己的序号是不是最小的
        List<String> children = zk.getChildren(path, null);
        Collections.sort(children);

        String formerNode = "";  //前一个节点，用于监听
        for (int i = 0; i < children.size(); i++) {
            String node = children.get(i);
            if (nodeVal.equals(path + "/" + node)) {
                if (i == 0) {
                    //第一个
                    System.out.println("我被选为leader节点了");
                } else {
                    formerNode = children.get(i - 1);
                }
            }
        }
        if (!"".equals(formerNode)) {
            //自己不是第一个，如果是第一个formerNode应该没有值
            System.out.println("我竞选失败了");
            //3、监听前一个节点的删除事件，如果删除了，重新进行选举
            zk.getData(path + "/" + formerNode, event -> {
                System.out.println(event.getType() + "---" + event.getPath() + "---" + event.getState());
                try {
                    if (Objects.equals(event.getType(), Event.EventType.NodeDeleted)) {
                        selectionFair(path, zk, nodeVal);
                    }
                } catch (Exception e) {
                }
            }, null);
        }
        //System.out.println("children:" + children);
    }

}
