package com.boot.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 使用配置类的方式
 * TomcatServletWebServerFactory 对象装载到容器中 这个对象对内置tomcat参数设置调优
 *
 *
 * ServletWebServerFactoryConfiguration  可以看看源码
 */

@SpringBootConfiguration
public class WebServerConfiguration {

    @Bean
    public TomcatServletWebServerFactory createTomcatServletWebServerFactory(){

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(10010);
        ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        tomcat.addErrorPages(errorPage);
        tomcat.addInitializers(new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                System.out.println(servletContext.getServletContextName());
                servletContext.setAttribute("global param", "小王");
            }
        });
        return tomcat;
    }

}
