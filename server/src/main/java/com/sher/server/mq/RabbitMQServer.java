package com.sher.server.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/16.
 */
public class RabbitMQServer {

    private final static String HOST = "localhost";
    private final static int PORT = 5672;
    private final static int TIME_OUT = 1000;

    private final static String QUEUE_NAME = "hello1";
    private final static String ROUT_KEY = "queue";
    private final static String EXCHANGE_NAME = "test1";

    public static void main(String args[]) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setConnectionTimeout(TIME_OUT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //申明交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");


        //通道创建自动销毁，随机分配队列
        String queue = queue(channel,QUEUE_NAME);
        System.out.println("队列名称： "+ queue);


        //添加绑定信息
        /***
         *   Exchange ==> routing_key ==> queue
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUT_KEY);


        //创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);

        //循环获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            Thread.sleep(1000);
            System.out.println("接收到的消息： " + new String(delivery.getBody()));
            consumer.getConsumerTag();
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }


    }


    public static String queue(Channel channel,String queueName) throws IOException {
        if(queueName == null){
            return channel.queueDeclare().getQueue();
        }else{
            channel.queueDeclare(QUEUE_NAME, false, false, false, null).getQueue();
            return queueName;
        }
    }


    public static void bind(Channel channel,String queueName,String exchangeName,String routkey) throws IOException {
        String key[] = new String[]{RoutKeyType.ERROR.toString(),RoutKeyType.INFO.toString(),RoutKeyType.WARINT.toString()};
        for(int i = 0 ; i < key.length; i++){
            channel.queueBind(queueName,exchangeName,routkey);
        }
    }

    public static void doBind(Channel channel,String queueName,String exchangeName,String routkey){

    }


}
