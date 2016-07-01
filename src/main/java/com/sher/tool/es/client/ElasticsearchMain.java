package com.sher.tool.es.client;

import com.sher.tool.es.client.model.DocumentIndex;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cloudsher on 2016/5/18.
 */
public class ElasticsearchMain {

    private static Client client;

    public static void main(String args[]){
        try {
            client = ElasticsearchClient.transportClient("127.0.0.1");
//            documentIndex();

//            print(client.prepareIndex("user", "Java").setSource(new MapObj().put("name","ÕÅÈý").build()).get().isCreated());

            print(client.prepareGet("user","Java","").get().getSourceAsString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void print(Object str){
        System.out.println(str);
    }

    public static void documentIndex(){
        GetResponse response = client.prepareGet().get();
        print(response.getSourceAsString());
    }

    public static String json(){
        try {
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            jsonBuild.startObject()
                    .field("","")
                    .endObject();
            return jsonBuild.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


     static class MapObj{
        private  Map<String,Object> map = new HashMap<>();

        public MapObj put(String key,Object obj){
            this.map.put(key,obj);
            return this;
        }

        public Map build(){
            return this.map;
        }

    }

}
