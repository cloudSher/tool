package com.sher.tool.pattern.reactor;

/**
 * Created by cloudsher on 2016/7/11.
 *
 * 服务端
 */
public class Server {

    public static void main(String args[]){
        InitDispatcher init = new InitDispatcher();
        EventHandler handler = new EventHandler();
        init.register_event(handler,"init");
        init.start();
//        accect();
    }


    /**
     *
     *  服务端接受请求，采用异步的方式去处理接收到的请求
     */
    public void accect(){

    }

}
