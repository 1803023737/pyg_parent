package com.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableConfigurationProperties(jdbcProperties.class)
@Configuration
public class JdbcConfiguration {

    //private DataSourceProperties dataSourceProperties;

    //@Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        return druidDataSource;
    }


}
