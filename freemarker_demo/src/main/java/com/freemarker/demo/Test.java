package com.freemarker.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        //配置对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板所在目录
        try {
            configuration.setDirectoryForTemplateLoading(new File("F:\\work_idea\\boot\\pyg_parent\\freemarker_demo\\src\\main\\resources"));
            //设置字符集
            configuration.setDefaultEncoding("utf-8");
            //获取魔板对象
            Template template = configuration.getTemplate("test.ftl");
            //创建数据模型（对象也可以是map）
            Map map=new HashMap();
            map.put("name", "张三");
            map.put("message", "欢迎使用freemarker");//这边如果缺失对象  会程序报错的  必须页面需要获得的变量必须在这边有设置值
            //map.put("success", true);
            map.put("success", false);

            //list对象
            List goodsList=new ArrayList();
            Map goods1=new HashMap();
            goods1.put("name", "苹果");
            goods1.put("price", 5.8);
            Map goods2=new HashMap();
            goods2.put("name", "香蕉");
            goods2.put("price", 2.5);
            Map goods3=new HashMap();
            goods3.put("name", "橘子");
            goods3.put("price", 3.2);
            goodsList.add(goods1);
            goodsList.add(goods2);
            goodsList.add(goods3);
            //将list对象封装到map中
            map.put("goodsList", goodsList);


            //日期
            map.put("today", new Date());

            //数字
            map.put("point", 1111111111);


            //判断运算符是否存在
            map.put("aaa", "aaa");

            //创建输出流对象
            FileWriter fileWriter = new FileWriter("d:\\test.html");
            //输出  魔板对象
            try {
                template.process(map, fileWriter);
                //关闭
                fileWriter.close();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
