package com.sher.server.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/11/9.
 */
public class LeaderElection extends MainClient {

    public LeaderElection(String connection,String root){
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

    public void election(String name) throws KeeperException, InterruptedException, UnknownHostException {
        Stat stat = zk.exists(name, false);
        if (stat == null){
            doCreateLeader(root + "/" + name);
        }else{
            doFollower();
        }
    }

    private void doCreateLeader(String name) throws KeeperException, InterruptedException, UnknownHostException {
        System.out.println("become be leader ...");
        zk.create(name,InetAddress.getLocalHost().getAddress(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
    }


    public void findLeader(String name) throws KeeperException, InterruptedException, UnknownHostException {
        byte[] data = zk.getData(root + "/" + name, true, null);
        if(data != null){
            doFollower();
        }else{
            String s = zk.create(root + "/" + name, InetAddress.getLocalHost().getAddress(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            if(s==null){
                synchronized (mutex){
                    mutex.wait();
                }
            }else{
                doLeader();
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getPath().equals(root+"/leader") && event.getType() == Event.EventType.NodeCreated){
            System.out.println("process event action ...");
            super.process(event);
            doFollower();
        }

    }

    private void doLeader(){
        System.out.println("become be Leader ....");
    }

    private void doFollower() {
        System.out.println("become be follower ...");
    }


    public static void main(String args[]){
        LeaderElection election = new LeaderElection("localhost:2181","/groups");
        try {
            election.election("/leader");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
