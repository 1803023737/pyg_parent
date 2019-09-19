package com.boot.config;

import com.boot.bean.EncodingConvert;
import com.boot.bean.GBKEncodingConvert;
import com.boot.bean.UTF8EncodingConvert;
import com.boot.condition.GBKCondition;
import com.boot.condition.UTF8Condition;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

//Conditional 一般配合接口condition一起运行使用  只有接口实现类返回true才装配  否则不装配
//作用于方方法，只对该方法生效，作用于类，则对类起作用
//参数里面多个condition接口实现类数组，代表都返回true才装配
@SpringBootConfiguration
public class EncodingConvertConfiguration {

    @Bean
    @Conditional(UTF8Condition.class)//条件注解
    public EncodingConvert createUTF8EncodingConvert(){
        return new UTF8EncodingConvert();
    }

    @Bean
    @Conditional(GBKCondition.class)//条件注解
    public EncodingConvert createGBKEncodingConvert(){
        return new GBKEncodingConvert();
    }
}
