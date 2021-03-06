package com.zylear.j2eelab.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
/**
 * Created by xiezongyu on 2018/8/21.
 */
public class ZooKeeperTest {

    public static void main(String[] args) throws Exception {
        Watcher watcher = new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("触发了" + event.getType() + "事件！");
            }
        };


        // 1.连接ZooKeeper服务器
        // 第一个参数：ZooKeeper服务器的连接地址，如果ZooKeeper是集群模式或伪集群模式（即ZooKeeper服务器有多个），那么每个连接地址之间使用英文逗号间隔，单个连接地址的语法格式为"主机IP:ZooKeeper服务器端口号"；
        // 第二个参数：session超时时长（单位：毫秒）
        // 第三个参数：用于监控目录节点数据变化和子目录状态变化的Watcher对象
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, watcher);

        // 2.创建名为"/rootNode"的持久目录节点
        zooKeeper.create("/rootNode", "RootNodeData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 判断指定目录节点是否存在
        System.out.println("'/rootNode'节点状态：" + zooKeeper.exists("/rootNode", true));
        // 获取"rootNode"节点上的数据
        System.out.println("'/rootNode'节点上数据：" + new String(zooKeeper.getData("/rootNode", false, null)));

        // 3.创建子目录节点
        // 在"rootNode"节点下创建一个名为"ChildNode1"的子目录节点
        zooKeeper.create("/rootNode/ChildNode1", "ChildNode1Data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 在"rootNode"节点下创建一个和"ChildNode1"同级的名为"ChildNode2"的子目录节点
        zooKeeper.create("/rootNode/ChildNode2", "ChildNode2Data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 取出目录节点"rootNode"下的所有子目录节点
        System.out.println("目录节点'/rootNode'下的所有子目录节点有：" + zooKeeper.getChildren("/rootNode", true));
        // 修改名为"ChildNode2"的目录节点数据
        zooKeeper.setData("/rootNode/ChildNode2", "NewChildNode2Data".getBytes(), -1);

//        // 4.操作目录节点
//        // 删除"/rootNode/ChildNode1"目录节点
//        zooKeeper.delete("/rootNode/ChildNode1", -1);
//        // 判断"/rootNode/ChildNode1"目录节点是否存在
//        System.out.println("'/rootNode/ChildNode1'节点状态：" + zooKeeper.exists("/rootNode/ChildNode1", false));
//        // 删除"/rootNode/ChildNode2"目录节点
//        zooKeeper.delete("/rootNode/ChildNode2", -1);
//        // 删除"/rootNode"目录节点
//        zooKeeper.delete("/rootNode", -1);

        // 5.关闭与ZooKeeper的连接
        zooKeeper.close();
    }
}
