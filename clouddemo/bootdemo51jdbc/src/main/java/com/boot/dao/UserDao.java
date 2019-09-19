package com.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addUser() {
        String sql = "insert into user (username,birthday,sex,address) values (?,?,?,?)";
        Object[] params = new Object[]{"科比", "2019-05-11", "1", "泉州市"};
        int update = jdbcTemplate.update(sql, params);
        if (update != 1) return false;
        return true;
    }

    @Transactional//默认只针对运行时异常有效  事务回滚  非运行时异常事务无效  事务不会回滚
    //@Transactional(rollbackFor = Exception.class)//对所有异常都可以回滚
    //@Transactional(noRollbackFor = NullPointerException.class)//空指针属于运行时异常   这边排除空指针  不回滚
    public void addUserBatch() {
        for (int i = 0; i < 10; i++) {
            String sql = "insert into user (username,birthday,sex,address) values (?,?,?,?)";
            Object[] params = new Object[]{"科比", "2019-05-11", "1", "泉州市"};
            if (i == 5) {
                throw new RuntimeException("异常！");
                //throw new FileNotFoundException("异常！");
                //throw new NullPointerException("null exception!");
            }
            jdbcTemplate.update(sql, params);
        }
    }


    //调用非public修饰的@Transactional的方法  不回滚
    public void addUserBatch2() {
        add();
    }

    @Transactional
    protected void add() {
        for (int i = 0; i < 10; i++) {
            String sql = "insert into user (username,birthday,sex,address) values (?,?,?,?)";
            Object[] params = new Object[]{"科比", "2019-05-11", "1", "泉州市"};
            if (i == 5) {
                throw new RuntimeException("异常！");
                //throw new FileNotFoundException("异常！");
                //throw new NullPointerException("null exception!");
            }
            jdbcTemplate.update(sql, params);
        }
    }

}
