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

    /**
     *  客户端获取锁资源过程，
     *  1. 首先，客户端在zk上注册临时有序节点
     *  2. 获取父节点下面所有的注册的临时有序节点，并排序，
     *  3. 比较自己创建的节点与zk服务列表中最小的节点，如果是，可以获取资源，执行业务操作，
     *      如果不是，说明已经有别的节点去操作资源，当前线程阻塞
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
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

    /**
     * 增加锁操作，当前线程阻塞，
      todo 分布式锁的性能问题
     * @param lock
     */
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
            //如果节点被删除，当前节点可以获取锁资源，
            try {
                //重新获取锁操作
                getLock();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 业务逻辑的处理
     *
     *  如果当前节点执行成功之后，释放锁资源，删除zk中的节点目录，
     *  异步通知其他服务节点去获取锁资源去操作
     *
     */
    private void doAction() throws KeeperException, InterruptedException {
        System.out.println("do some thing ...");
        //业务逻辑处理完之后，释放锁，
        zk.delete(this.myLock,-1);
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
