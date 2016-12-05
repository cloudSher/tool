package com.sher.tool.net.http;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/12/1.
 */
public class HttpRequest {

    public static void main(String args[]) throws IOException {
        request("http://news.ifeng.com/a/20161201/50345113_0.shtml");
    }


    public static void request(String url) throws IOException {
        URL u = new URL(url);
        URLConnection connection = u.openConnection();
//        OutputStream out = connection.getOutputStream();
        connection.setDoInput(true);
        InputStream stream = connection.getInputStream();
        System.out.println(transform(stream));
    }

    public static String transform(InputStream in) throws IOException {
        System.out.println("stream len is :" + in.available());
        StringWriter writer = new StringWriter();
        if(in != null){
            int len;
            byte[] buffer = new byte[1024];
            while((len = in.read(buffer))!= -1){
                writer.append(new String(buffer,0,len));
            }
            writer.flush();
        }
        return writer.toString();
    }

}
