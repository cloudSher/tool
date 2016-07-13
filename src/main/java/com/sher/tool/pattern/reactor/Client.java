package com.sher.tool.pattern.reactor;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.SocketChannel;

/**
 * Created by cloudsher on 2016/7/11.
 */
public class Client {


    public static void main(String args[]){
        send("f://install.log");
    }


    public static void send(String file){
        init();
        File log;
        try {
            if((log = new File(file)).exists()){
                RandomAccessFile accessFile = new RandomAccessFile(log,"rw");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SocketChannel init(){

        return null;
    }
}
