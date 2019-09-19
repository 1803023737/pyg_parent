package com.boot;

import com.boot.dao.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 * 1.datasouce 自动装配  加入数据库依赖 + 配置文件加入数据库四个参数  自动装配好数据库datasource对象
 * 2。可以自己装配自己的数据库源  覆盖boot默认的数据源
 *
 *Transactional  必须纳入spring管理对象的共有方法里面
 *
 * 事务生效  方法上面一定要加Transactional注解  如果直接调用方法没有注解，里面调用方法有注解，依然不生效！
 *
 */

@SpringBootApplication
@EnableTransactionManagement
public class App {

    public static void main(String[] args) throws FileNotFoundException {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        try {
             DataSource dataSource = context.getBean(DataSource.class);
            System.out.println(dataSource.getClass().getName());
            Connection connection = dataSource.getConnection();
            //String schema = connection.getSchema();
            String catalog = connection.getCatalog();
            System.out.println("catalog:"+catalog);
            //System.out.println("schema:"+schema);
            connection.close();
            System.out.println("========================================");
            JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
            System.out.println(jdbcTemplate);

            //执行sql测试下
            UserDao userDao = context.getBean(UserDao.class);
            //userDao.addUserBatch();
            userDao.addUserBatch2();

            //DataSource createDataSource = (DataSource) context.getBean("createDataSource");
            //System.out.println(createDataSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //DataSource createDataSource = (DataSource) context.getBean("dataSource");
        //System.out.println(createDataSource);
    }
}
