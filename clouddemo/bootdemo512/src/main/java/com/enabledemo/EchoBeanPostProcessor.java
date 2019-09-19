package com.enabledemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

//bean装配的前后
public class EchoBeanPostProcessor implements BeanPostProcessor {

    private List<String> packageList;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        for (String packageName : packageList) {
        if (bean.getClass().getName().startsWith(packageName)){
            //如果是配置的包名的类，则打印
            System.out.println("echo bean:"+bean.getClass().getName());
        }
    }
        return bean;
}

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public List<String> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<String> packageList) {
        this.packageList = packageList;
    }
}
