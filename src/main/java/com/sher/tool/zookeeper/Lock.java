package com.sher.tool.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Lock {

    private ZooKeeper zk;

    public Lock(){
        try {
            zk = ZookeeperClientDemo.createZK("",0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]){

    }
}
