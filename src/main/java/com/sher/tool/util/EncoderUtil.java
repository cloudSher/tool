package com.sher.tool.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/10/20.
 */
public class EncoderUtil {



    public static String encoder(byte[] data,String type) throws UnsupportedEncodingException {
        if(type == null){
            type = "UTF-8";
        }
        String result;
        result = new String(data,type);
        return result;
    }


    public static byte[] decoder(String data,String type){
        return data.getBytes(type==null?Charset.defaultCharset():Charset.forName(type));
    }
}
