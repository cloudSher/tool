package com.sher.tool.base.test.String;

import java.io.*;
import java.net.Socket;

/**
 * Created by cloudsher on 2016/6/1.
 *
 *
 */
public class StringUtils {

    public static void println(String str){
        System.out.println(str);
    }


    public static String getResult() throws IOException {
        PrintWriter writer = new PrintWriter("f://bb.json");

        FileOutputStream outStream = new FileOutputStream("f://bb.json");
        outStream.write(122342342);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.writeTo(outStream);
        byte[] bytes = out.toByteArray();
        for(byte b : bytes){
            System.out.println(b);
        }
//        ByteArrayInputStream in = new ByteArrayInputStream();

        return new String(out.toByteArray(),"utf-8");
    }

    public static void main(String args[]) throws IOException {
        System.out.println(getResult());

        Socket socket = new Socket();
        OutputStream outputStream = socket.getOutputStream();
//        outputStream.
    }

}
