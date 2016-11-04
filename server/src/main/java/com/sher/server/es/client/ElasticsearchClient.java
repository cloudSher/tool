package com.sher.server.es.client;

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
 *  1.node client:�����ݽڵ�(none data node)��ݼ��뼯Ⱥ������֮�����Լ����洢�κ����ݣ�������֪�������ڼ�Ⱥ�еľ���λ�ã������ܹ�ֱ��ת�����󵽶�Ӧ�Ľڵ���
 *  2.Transport client:�����Ĵ���ͻ����ܹ���������Զ�̼�Ⱥ�����Լ������뼯Ⱥ��ֻ�Ǽ�ת���������Ⱥ�еĽڵ�
 *
 *  ����client ����ͨ��9300�ͼ�Ⱥ����
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
