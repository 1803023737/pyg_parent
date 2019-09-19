package com.boot.config;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import java.io.File;

/**
 * tomcat 调优  第一种方法  将这个自定义对象赚阿挂载到spring容器中
 */
//@Component
public class MyWebServerFactoryCustomizer implements WebServerFactoryCustomizer{

    //代码形式操作内置tomcat对象

    @Override
    public void customize(WebServerFactory factory) {
        //获得web server工厂对象
        System.out.println(factory.getClass());
        //获得tomcat server factory对象
        TomcatServletWebServerFactory tomcat= (TomcatServletWebServerFactory) factory;
        //设置tomcat参数
        tomcat.setPort(10001);
        //设置基本目录
        tomcat.setBaseDirectory(new File("e:/temp/tomcat"));
        //log设置
        tomcat.addContextValves(getAccessLogValve());

        //调优  定制容器
        tomcat.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
    }

    private AccessLogValve getAccessLogValve(){
        AccessLogValve log=new AccessLogValve();
        log.setDirectory("e:/logs");
        log.setEnabled(true);
        //样式
        log.setPattern("common");
        //前缀后缀
        log.setPrefix("spring boot-access-log");
        log.setSuffix(".txt");
        return log;
    }

}

class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer{

    @Override
    public void customize(Connector connector) {
        //String protocol = connector.getProtocol();
        //System.out.println("protocol:"+protocol);
        //System.out.println(connector.getProtocolHandler().getClass());
        //连接器
        Http11NioProtocol http11NioProtocol=(Http11NioProtocol)connector.getProtocolHandler();
        //2000连接数   500线程
        http11NioProtocol.setMaxConnections(2000);//最大连接数
        http11NioProtocol.setMaxThreads(500);//最大并发数
    }
}
