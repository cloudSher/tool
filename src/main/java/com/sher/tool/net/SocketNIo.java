package com.sher.tool.net;

import com.sher.tool.pattern.reactor.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/1.
 */
public class SocketNIo {

    private static ServerSocketChannel serverSocket;
    private static Selector selector;

            public static void server(int port) throws IOException {
                serverSocket = ServerSocketChannel.open();
                selector = Selector.open();
                serverSocket.socket().bind(new InetSocketAddress(port));
                serverSocket.configureBlocking(false);
                SelectionKey key = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
                key.attach(new Acceptor());
            }

        public static void run(){
            while(true){
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()){
                SelectionKey next = iterator.next();
                action(next);
            }
            keys.clear();

        }
    }

    public static void action(SelectionKey key){
        System.out.println(key.interestOps());
        System.out.println("attachment ;"  + key.attachment());
        Runnable attachment = (Runnable) key.attachment();
        attachment.run();
    }

    static class Reader implements Runnable{
        private SelectionKey key;
        Reader(SelectionKey key){
            this.key = key;
        }
        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                SocketChannel channel = (SocketChannel) key.channel();
                channel.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            key.interestOps(SelectionKey.OP_WRITE);
            System.out.println("read" + buffer.toString());
        }
    }

    static class Writer implements Runnable{
        private SelectionKey key;

        Writer(SelectionKey key){
            this.key = key;
        }

        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            SocketChannel channel = (SocketChannel) key.channel();
            try {
                channel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            key.cancel();
//            key.selector().wakeup();
        }
    }

    /**
     * read and write handler
     */
    static class Handler implements Runnable{
        private Selector selector;
        private SocketChannel channel;

        public Handler(Selector selector, SocketChannel accept) {
            try {
                this.selector = selector;
                this.channel = accept;
                channel.configureBlocking(false);
                SelectionKey key = channel.register(selector, 0);
                key.attach(this);
                key.interestOps(SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
            selector.wakeup();
        }

        @Override
        public void run() {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey next = iterator.next();
                if((next.readyOps() & SelectionKey.OP_READ )== SelectionKey.OP_READ){
                    new Reader(next).run();
                }else if((next.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE){
                    new Writer(next).run();
                }
            }
            System.out.println();
        }
    }

    static class Acceptor implements Runnable{

        @Override
        public void run() {
            try {
                SocketChannel accept = serverSocket.accept();
                if(accept != null){
                    System.out.println("channel:"+accept+","+accept.isConnected());
                }
                new Handler(selector,accept);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]){
        try {
            server(8088);
            run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
