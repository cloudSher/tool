package com.sher.tool.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by cloudsher on 2016/3/24.
 */
public class ZookeeperClientDemo {


    public static void main(String args[]){
        String host= "127.0.0.1:2181";
        try {
            Watcher watcher = new MyWatcher();
            ZooKeeper zooKeeper = new ZooKeeper(host,3000,watcher);
            zooKeeper.exists("/",watcher);
            znode(zooKeeper);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void znode(ZooKeeper zk){
        try {
//            zk.create("/root","MyRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//            zk.create("/root/children","children222".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

            zk.setData("/root/children", "children222".getBytes(), -1);
            List<String> children = zk.getChildren("/root", true);
            System.out.println(new String(zk.getData("/root", true, null)).toString());
            children.forEach(System.out::print);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void shardLock(ZooKeeper zooKeeper){
//        zooKeeper.create("/Lock");

    }

    static class MyWatcher implements Watcher{

        public void process(WatchedEvent watchedEvent) {
            System.out.println(watchedEvent.getPath());
        }
    }



}
