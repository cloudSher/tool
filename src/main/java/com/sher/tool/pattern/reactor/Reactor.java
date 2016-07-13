package com.sher.tool.pattern.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by cloudsher on 2016/7/12.
 */
public final class Reactor implements Runnable{

    final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    private int port;               //端口

    Reactor(int port) throws IOException {
        this.port = port;
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        select();
    }

    public static void main(String args[]){
        try {
            Reactor reactor = new Reactor(8888);
            Thread t = new Thread(reactor);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    dispatch(key);
                }
                keys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void dispatch(SelectionKey key){
        Runnable run = (Runnable) key.attachment();
        System.out.println("dispatch method has run");
        if(run != null)
            run.run();
    }


    /**
     * 利用java serversocket 实现sync 机制
     */
    public void select(){
        try {
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(new Acceptor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  handler 执行事件类
     */
    final class Handler implements Runnable{

        final Selector selector;
        final SocketChannel socketChannel;
        final SelectionKey key;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer outPut = ByteBuffer.allocate(2014);

        static final int READ = 0;
        static final int SEND = 1;
        int state = READ;

        Handler(Selector selector,SocketChannel channel) throws IOException {
            this.selector = selector;
            this.socketChannel = channel;
            socketChannel.configureBlocking(false);
            key = socketChannel.register(selector, 0);
            key.attach(this);
            key.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {
            if(state == READ) read();
            else if (state == SEND) send();
        }

        public void read(){
            try {
                socketChannel.read(input);
                if(inputIsComplete()){
                    process(input);
                    state = SEND;
                    key.interestOps(SelectionKey.OP_WRITE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean inputIsComplete(){
            return true;
        }

        public boolean outputIsComplete(){
            return true;
        }

        public void send(){
            try {
                socketChannel.write(outPut);
                if(outputIsComplete()){
                    System.out.println("socket channel write ");
                    key.cancel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void process(Buffer buffer){
            System.out.println("============I am working" + buffer.toString());
        }
    }


    final class Acceptor implements  Runnable{

        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if(channel != null){
                    System.out.println("-----");
                    new Handler(selector,channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
