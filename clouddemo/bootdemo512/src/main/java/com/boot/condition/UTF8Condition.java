package com.boot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UTF8Condition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String encoding=System.getProperty("file.encoding");
        System.out.println("encoding:"+encoding);
        if (encoding!=null){
            //判断是否为gbk
            return "utf-8".equals(encoding.toLowerCase());
        }
        return false;
    }
}
