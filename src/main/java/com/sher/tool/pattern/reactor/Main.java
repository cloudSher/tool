package com.sher.tool.pattern.reactor;

/**
 * Created by cloudsher on 2016/7/11.
 */
public class Main {

    public static void main(String args[]){
        InitDispatcher init = new InitDispatcher();
        EventHandler handler = new EventHandler();
        init.register_event(handler,"init");
        init.start();
    }
}
