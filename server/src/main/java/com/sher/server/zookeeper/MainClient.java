package com.sher.server.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MainClient implements Watcher {

    public  ZooKeeper zk;
    public int sessionTimeout = 1000;
    protected String root;
    protected Integer mutex;

    public MainClient(String connection){
        if(zk == null){
            try {
                zk = new ZooKeeper(connection,sessionTimeout,this);
                mutex = new Integer(-1);
            } catch (IOException e) {
                e.printStackTrace();
                zk = null;
            }
        }
    }

    public MainClient() {
    }


    @Override
    public void process(WatchedEvent event) {
        synchronized (mutex){
            mutex.notifyAll();
        }
        System.out.println(event.toString());
    }
}
