package com.sher.server.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/16.
 *
 *  消费者
 */
public class RabbitMQServer {

    private final static String HOST = "localhost";
    private final static int PORT = 5672;
    private final static int TIME_OUT = 1000;

    private final static String QUEUE_NAME = "hello1";
    private final static String ROUT_KEY = "queue";
    private final static String EXCHANGE_NAME = "test1";
    private String name;

    RabbitMQServer(String name){
        this.name = name;
    }

    /**
     * get channel
     * @return
     * @throws IOException
     */
    public Channel channel() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setConnectionTimeout(TIME_OUT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }

    /**
     * 声明交换器
     * @throws IOException
     */
    public void exchange(Channel channel) throws IOException {
        //申明交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
    }

    public void queue(Channel channel) throws IOException {
        //通道创建自动销毁，随机分配队列
        String queue = queue(channel,QUEUE_NAME);
        System.out.println("队列名称： "+ queue);

    }

    public void bind(Channel channel) throws IOException {

        //添加绑定信息
        /***
         *   Exchange ==> routing_key ==> queue
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUT_KEY);
    }

    public QueueingConsumer consumer(Channel channel) throws IOException {
        //创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);
        return consumer;
    }


    /**
     *
     * @param channel
     * @throws IOException
     * @throws InterruptedException
     */
    public void consume(Channel channel) throws IOException, InterruptedException {
        QueueingConsumer consumer = consumer(channel);
        //循环获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            Thread.sleep(1000);
            System.out.println("server"+this.name+":接收到的消息： " + new String(delivery.getBody()));
            consumer.getConsumerTag();
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }

    public static void execute() throws IOException, InterruptedException {
        new Thread(()->{
            RabbitMQServer server = new RabbitMQServer("1");
            Channel channel = null;
            try {
                channel = server.channel();
                server.exchange(channel);
                server.queue(channel);
                server.bind(channel);
                server.consume(channel);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(()->{
            RabbitMQServer server1 = new RabbitMQServer("0");
            Channel channel1 = null;
            try {
                channel1 = server1.channel();
                server1.exchange(channel1);
                server1.queue(channel1);
                server1.bind(channel1);
                server1.consume(channel1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


    }


    public static void main(String args[]) throws IOException, InterruptedException {
        execute();
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
