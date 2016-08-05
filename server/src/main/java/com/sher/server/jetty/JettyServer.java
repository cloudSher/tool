package com.sher.server.jetty;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import java.security.ProtectionDomain;

/**
 * Created by cloudsher on 2016/7/19.
 *
 *  jetty
 */
public class JettyServer {

    public static void main(String args[]){
        Server server = createServer("f://aa.war",8888);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Server createServer(String path,int port){
        Server server = new Server(port);
        server.setStopAtShutdown(true);
        ProtectionDomain protectionDomain = JettyServer.class.getProtectionDomain();
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar(path);
        server.setHandler(webAppContext);
        return server;
    }
}
