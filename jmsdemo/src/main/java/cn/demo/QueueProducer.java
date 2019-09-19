package cn.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {

    public static void main(String[] args) throws JMSException {

        //1创建连接工厂
        String brokerurl="tcp://192.168.25.135:61616";//链接地址
        ConnectionFactory factory=new ActiveMQConnectionFactory(brokerurl);
        //2创建链接
        Connection connection = factory.createConnection();
        //3启动链接
        connection.start();
        //4获取session  会话对象
        // 参数 第一个是否启用事务  true必须commit才能提交一系列事务
        // 第二个参数是消息确认方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//自动确认
        //5创建队列对象
        Queue queue = session.createQueue("test-quene");
        //6创建消息的生产者对象 queue 是distination的子类
        MessageProducer producer = session.createProducer(queue);
        //7 创建消息对象  不同类型的类型消息
        TextMessage textMessage = session.createTextMessage("welcome 欢迎来品优购！");
        //8 发送消息
        producer.send(textMessage);
        //9 关闭资源
        producer.close();
        session.close();
        connection.close();
    }



}
