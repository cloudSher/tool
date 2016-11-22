package com.sher.tool.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/11/22.
 */
public class FileChannelDemo {


    public static void fileTranto(String source,String dest) throws IOException {
        RandomAccessFile file = new RandomAccessFile(source,"r");
        FileChannel sc = file.getChannel();

        RandomAccessFile destFile = new RandomAccessFile(dest,"rw");
        FileChannel destFileChannel = destFile.getChannel();

        sc.transferTo(file.getFilePointer(),file.length(),destFileChannel);
    }

    public static void main(String args[]) throws IOException {
        fileTranto("f://alipay-sdk-JAVA-20160417220106.zip","f://alipay-back.zip");
    }
}
