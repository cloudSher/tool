package com.sher.server.zookeeper;

import com.sher.tool.util.CollectionUtil;
import com.sher.tool.util.EncoderUtil;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cloudsher on 2016/3/24.
 */
public class ZookeeperClientDemo {

    private static ZooKeeper zk;
    private static Watcher watcher;
    private static String host = "10.1.34.59:2181";
    private static Map<String,Object> cache = new HashMap<>();
    private static Map<String,String> nodePath =new HashMap<>();

    public static void main(String args[]){
        test();
    }

    public static void test(){
        try {
//            createZK(host,3000);
            createZK("10.1.34.24:2181",3000);
            //error opration ,since of no node for /aa/bb
            createZnode("/root","Test Data");
//            createZnode("/hello/world/test","Hello World Test");

            list("/");
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

    /****
     *
     * @param zooKeeper
     */
    public static void shardLock(ZooKeeper zooKeeper){
//        zooKeeper.create("/Lock");

    }

    public static void list(String root) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(root, watcher);
        try {
            getChildren(root);
            CollectionUtil.println(cache);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static void getChildren(String path) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        List<String> children = zk.getChildren(nodePath.get(path)==null?path:nodePath.get(path), watcher);
        if(children!= null || children.size()>0){
            for(String cl: children){
                System.out.println("current znode : "+cl);
                String znode = "/"+cl;
                nodePath.put(znode,nodePath.get(path)==null?znode:nodePath.get(path)+znode);
                byte[] data = zk.getData(nodePath.get(znode), true, null);
                if(data == null || data.length == 0){
                    getChildren(znode);
                }else{
                    cache.put(cl,EncoderUtil.encoder(data,null));
                }
                zNodeInfo(nodePath.get(znode));
            }
        }
    }

    public static void zNodeInfo(String path){
        try {
            Stat stat = zk.exists(path, true);
            System.out.println("" +
                    "*******************zNode info********************" +
                    "-- path :" + path+
                    "-- cTime :" +stat.getCtime()+
                    "-- czxid :" + stat.getCzxid()+
                    "-- data length :" + stat.getDataLength()+
                    "-- version :" + stat.getVersion()+
                    "**********************************************" +
                    "");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void createZnode(String path,String data){
        try {
            String s = zk.create(path, EncoderUtil.decoder(data, null), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(s);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static ZooKeeper createZK(String ip, int port) throws IOException {
        if(zk == null){
            watcher = new MyWatcher();
            zk = new ZooKeeper(ip,port,watcher);
        }
        return zk;
    }

    static class MyWatcher implements Watcher{

        public void process(WatchedEvent watchedEvent) {
            System.out.println("watchedEvent : "+watchedEvent.getType()+" event, watch path :"+watchedEvent.getPath());
        }
    }



}
