package com.sher.tool.net;

import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by cloudsher on 2016/5/17.
 */
public class SocketDemo {

    public static void main(String args[]){
        try {
            InetAddress inetAddress = InetAddress.getByName("10.1.34.24");
            int port = 9300;
            DatagramSocket socket = new DatagramSocket();
            socket.connect(inetAddress,port);
            if(socket.isConnected()){
                System.out.println("========");
            }
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /***
     *  udp socket impl
     * @param ip
     * @param port
     * @return
     */
    public static DatagramSocket socketToUdp(String ip,int port) throws SocketException, UnknownHostException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(ip);
        socket.connect(address,port);
        if(!socket.isConnected()){
            throw new NullPointerException("socket is not null");
        }
        return socket;
    }


    /***
     *  tcp/ip socket impl
     * @param ip
     * @param port
     * @param time
     * @return
     * @throws IOException
     */
    public static Socket socketToTcp(String ip,int port,int time) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(InetAddress.getByName(ip),port),time);
        if(!socket.isConnected())
            throw new NullPointerException("socket must not be null");
        return socket;
    }

    // socketChannel
    static class NewSocket{

        private SocketChannel channel;
        private ServerSocketChannel serverSocket;



    }
}
