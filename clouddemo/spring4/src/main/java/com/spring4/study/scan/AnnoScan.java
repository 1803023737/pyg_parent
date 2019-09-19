package com.spring4.study.scan;

import com.spring4.study.di.Myconfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = "com.spring4.study",excludeFilters =@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = Myconfig.class))
@Configuration
public class AnnoScan {
}
