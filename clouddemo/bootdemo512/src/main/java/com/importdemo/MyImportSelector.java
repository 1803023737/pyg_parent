package com.importdemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

//自定义ImportSelector  方法返回值是class全名的集合数组，该class会被spring容器托管
public class MyImportSelector implements ImportSelector {

    private String [] classNames={
            User.class.getName(),
            Role.class.getName(),
            MyConfiguration.class.getName()
    };

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableLog.class.getName());
        for (String key : annotationAttributes.keySet()) {
            System.out.println("key:"+key+",value:"+annotationAttributes.get(key));
        }
        return classNames;
    }
}
