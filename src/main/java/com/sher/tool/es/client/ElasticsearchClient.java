package com.sher.tool.es.client;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by cloudsher on 2016/5/18.
 *
 *  Java API
 *  1.node client:无数据节点(none data node)身份加入集群，换言之，它自己不存储任何数据，但是它知道数据在集群中的具体位置，并且能够直接转发请求到对应的节点上
 *  2.Transport client:轻量的传输客户端能够发送请求到远程集群。它自己不加入集群，只是简单转发请求给集群中的节点
 *
 *  两种client 都是通过9300和集群交互
 *
 */
public class ElasticsearchClient {


    public static void main(String args[]){
        get_index();
    }

    public static Client transportClient(String host) throws UnknownHostException {
        Client client = TransportClient.builder()
//                .settings(Settings.settingsBuilder().put("cluster.name", "elasticsearch").build())
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.1.34.65"), 9300));
        return client;
    }

    public static void get_index(){
        try {
            Client client = transportClient("10.1.34.24");
            GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
            response.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
