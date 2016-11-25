package com.sher.tool.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * Created by cloudsher on 2016/5/17.
 */
public class SocketDemo {

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
        Socket socket = new Socket(ip,port);
//        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(),port),time);
//        if(!socket.isConnected())
//            throw new NullPointerException("socket must not be null");
        return socket;
    }

    // socketChannel
    static class NewSocket{

        private SocketChannel channel;
        private ServerSocketChannel serverSocket;

    }

    public static void serverSocket(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        int count = 0;
        while(true){
            Socket accept = serverSocket.accept();
            count++;
            InputStream stream = accept.getInputStream();
            printStream(stream);
            OutputStream out = accept.getOutputStream();
            out.write(count);
            out.flush();
        }
    }

    public static void printStream(InputStream in) throws IOException {
        if(in != null){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while((len = in.read()) != -1){
                out.write(buffer,0,len);
            }
            //print
            System.out.println(new String(out.toByteArray()));
        }
    }

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String args[]) throws InterruptedException {
        new Server().run(8080);
        latch.await();
//        new Client().run(1000,"10.1.34.24",8080);

    }

    static class Server{

        public void run(int port){
            new Thread(()->{
                try {
                    serverSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            latch.countDown();
        }
    }

    static class Client{
        private int threadNum;
        public void run(int threadNum,String ip,int port){
            this.threadNum = threadNum;
            for(int i = 0 ; i < threadNum; i++){
                new Thread(()->{
                    try {
                        Socket socket = socketToTcp(ip, port, 1000);
                        InputStream stream = socket.getInputStream();
                        printStream(stream);
                        OutputStream out = socket.getOutputStream();
                        out.write("hello".getBytes());
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }





}
