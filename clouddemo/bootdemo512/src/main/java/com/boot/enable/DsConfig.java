package com.boot.enable;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DsConfig {

    @Autowired
    private JdbcConfiguration jdbcConfiguration;

    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setPassword(jdbcConfiguration.getPassword());
        druidDataSource.setUsername(jdbcConfiguration.getUsername());
        druidDataSource.setUrl(jdbcConfiguration.getUrl());
        druidDataSource.setDriverClassName(jdbcConfiguration.getDriverClassName());
        return druidDataSource;
    }

}
