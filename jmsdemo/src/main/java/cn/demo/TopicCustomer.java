package cn.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicCustomer {

    public static void main(String[] args) throws JMSException, IOException {

        //1创建连接工厂
        String brokerurl = "tcp://192.168.25.135:61616";//链接地址
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerurl);
        //2创建链接
        Connection connection = factory.createConnection();
        //3启动链接
        connection.start();
        //4获取session  会话对象
        // 参数 第一个是否启用事务  true必须commit才能提交一系列事务
        // 第二个参数是消息确认方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//自动确认
        //5创建topic对象
        Topic topic = session.createTopic("topic");

        //6创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7设置监听
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("提取的消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //等待键盘输入
        System.in.read();
        //  //9 关闭资源
        consumer.close();
        session.close();
        connection.close();

    }


}
