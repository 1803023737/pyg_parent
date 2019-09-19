package com.boot.servlet;

import com.boot.filter.TestFilter;
import com.boot.listener.StartAppListener;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * 2. 依赖于spring配置方式
 * 用配置类的方式注册 servlet，filter，listener
 *
 * 1. 注解的方式   依赖于setvlet3.0
 * 注册servlet filter listener 需要servlet3.0以上版本  当我们setvlet为3.0以下，则需要用spring配置的这种方式比较好
 *
 */

@SpringBootConfiguration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean createBookServlet(){
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new BookServlet(),"/book.do");
        return servletRegistrationBean;
    }

    /**
     * filter注册bean
     * @return
     */
    @Bean
    public FilterRegistrationBean createTestFilter(){
        FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/book.do"));//拦截servlet路径
        filterRegistrationBean.setFilter(new TestFilter());
        return filterRegistrationBean;
    }

    /**
     * 监听器的注册
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean createServletContextListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean=new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new StartAppListener());
        return servletListenerRegistrationBean;
    }

}
