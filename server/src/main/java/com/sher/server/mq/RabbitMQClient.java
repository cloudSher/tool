package com.sher.server.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/16.
 */
public class RabbitMQClient {

    private final static String HOST= "127.0.0.1";
    private final static int PORT = 5672;
    private final static int TIMEOUT=1000;
    private final static String QUEUE_NAME = "hello1";

    private final static String EXCHANGE_NAME = "test1";
    private final static String ROUT_KEY = "queue";


    public static void main(String args[]) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setConnectionTimeout(TIMEOUT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //申明交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");


        //声明队列
//        String queue = queue(channel,QUEUE_NAME);
//        System.out.println("队列名称： " + queue);

        //发送消息
        send(channel);

        channel.close();
        connection.close();
    }



    public static String queue(Channel channel,String queueName) throws IOException {
        if(queueName == null){
            return channel.queueDeclare().getQueue();
        }else{
            channel.queueDeclare(queueName,false,false,false,null);
        }
        return queueName;
    }

    public static void send(Channel channel) throws IOException {
        for(int i = 0 ; i < 10; i++){
            String message = "HELLO World" + i;
            //通道发送消息
            channel.basicPublish(EXCHANGE_NAME, ROUT_KEY,null,message.getBytes());
        }
    }


    public static void bind(Channel channel,String queueName,String exchangeName,String routkey) throws IOException {
        // 通道绑定交换器
        channel.queueBind(queueName,exchangeName,routkey);
    }


    /**
     * 随机分发不同routingkey
     * @return
     */
    public static String routkey(){
        String key[] = new String[]{RoutKeyType.WARINT.toString(),RoutKeyType.INFO.toString(),RoutKeyType.ERROR.toString()};
        return key[(int)Math.random() * 3];
    }




}
