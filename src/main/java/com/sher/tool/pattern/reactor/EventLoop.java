package com.sher.tool.pattern.reactor;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by cloudsher on 2016/7/11.
 */
public class EventLoop {


    public void select(String type){
        while(true){
            System.out.println("loop is "+ type);

        }
    }

    public void socket(){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

