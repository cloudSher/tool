package com.sher.server.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Lock extends MainClient{

    private String myLock;

    public Lock(String connection,String root){
        super(connection);
        this.root = root;
        try {
            Stat stat = zk.exists(root, false);
            if(stat == null){
                zk.create(root,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void check() throws KeeperException, InterruptedException {
        myLock = zk.create(root + "/lock_", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("my lock is " + myLock);
        getLock();
    }

    public void getLock() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(root, false);
        String[] array = children.toArray(new String[children.size()]);
        Arrays.sort(array);
        if(myLock.equals(root+"/"+array[0])){
            doAction();
        }else{
            doWaitForLock(array[0]);
        }
    }

    private void doWaitForLock(String lock) {
        try {
            Stat stat = zk.exists(root + "/" + lock, true);
            if(stat == null){
                getLock();
            }else{
                synchronized (mutex){
                    mutex.wait();
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getType() == Event.EventType.NodeDeleted){
            System.out.println("process event action");
            super.process(event);
            doAction();     // 一定会是顺序执行吗？
        }
    }

    private void doAction() {
        System.out.println("do some thing ...");
    }


    public static void main(String args[]){
        Lock lock = new Lock("localhost:2181","/locks");
        try {
            lock.check();
            Thread.sleep(10000);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
