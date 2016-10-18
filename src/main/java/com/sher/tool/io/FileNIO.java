package com.sher.tool.io;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.file.Path;

/**
 * Created by cloudsher on 2016/6/20.
 */
public class FileNIO implements FileIOInterface{


    public static void read(String path){
        try {
            if(path != null){
                File file = new File(path);
                if(!file.exists())
                    throw new FileNotFoundException("file is not exist");
                RandomAccessFile aFile = new RandomAccessFile(file,"rw");
                FileChannel channel = aFile.getChannel();
                int len,size = 0;
                ByteBuffer buf = ByteBuffer.allocate(47);
                while((len = channel.read(buf)) != -1){
                    System.out.println("buf sequence is :" + len);
                    buf.flip();             //
                    while (buf.hasRemaining()){
                        System.out.println("byte is :"+ (char)buf.get());
                    }
                    buf.clear();
                    size++;
                }
                aFile.close();
                System.out.println("size is :" + size * 47);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void write(String fromPath,String toPath){
        try {
            if(!new File(fromPath).exists()){
                throw new FileNotFoundException("from path must not null");
            }

            RandomAccessFile fromFile = new RandomAccessFile(fromPath,"rw");
            RandomAccessFile toFile = new RandomAccessFile(toPath,"rw");

            FileChannel fromFileChannel = fromFile.getChannel();
            FileChannel toFileChannel = toFile.getChannel();

            int position = 0;
            long size = fromFileChannel.size();

            fromFileChannel.transferTo(position,size,toFileChannel);
            System.out.println(" from file size is : " + size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selector(String path){
        try {
            //
            Selector selector = Selector.open();

            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.register(selector,0);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        String fromPath = "f://crawl/blog.csdn.net/d5447b025c261712f0a6188560c94c51.json";
        String toPath = "f://bb.json";
//        read(fromPath);
        write(fromPath, toPath);
    }


    public static void write_stream(String url,String dest){
        try {
            URL domain = new URL(url);
            InputStream inputStream = domain.openConnection().getInputStream();

            RandomAccessFile out = new RandomAccessFile(dest,"rw");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            FileChannel channel = out.getChannel();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buff)) != -1){
                buffer.clear();
                buffer.put(buff,0,len);
                buffer.flip();
                channel.write(buffer);
            }
            out.close();
            channel.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void handler(String in, String out) {
        write_stream(in,out);
    }
}
