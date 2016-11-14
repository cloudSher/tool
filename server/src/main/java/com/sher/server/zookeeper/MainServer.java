package com.sher.server.zookeeper;

import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MainServer extends ZooKeeperServerMain{

    private static String DEFAULT_PATH = "D:\\zookeeper-3.5.2-alpha\\conf\\";
    private static String DEFAULT_CONFIG = "zoo.cfg";

    public static void main(String args[]){
        MainServer server = new MainServer();
        String config = server.default_config().toString();
        try {
            server.initializeAndRun(new String[]{config});
        } catch (QuorumPeerConfig.ConfigException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File default_config(){
        return new File(DEFAULT_PATH + DEFAULT_CONFIG);
    }

    /***
     * clientPort=2181
       dataDir=e://zookeeper/data
       syncLimit=5
       initLimit=10
       tickTime=2000
     * @return
     * @throws IOException
     */
    public File tempConfigFile() throws IOException {
        File config = new File(tempFile(),DEFAULT_CONFIG);
        FileWriter writer = new FileWriter(config);
        writer.write("clientPort=2181\n");
        File data = new File(tempFile(),"data");
        if(!data.exists()){
            data.mkdir();
        }
        writer.write("dataDir=" + data.toString() + "\n");
        writer.write("syncLimit=5\n");
        writer.write("initLimit=10\n");
        writer.write("tickTime=2000\n");
        return config;
    }

    public File tempFile(){
        return new File(System.getProperty("user.path"));
    }

}
