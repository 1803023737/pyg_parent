package com.pinyougou.solrutil;

import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SolrUtil {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    public void importData(){
        TbItemExample example = new TbItemExample();
        example.createCriteria().andStatusEqualTo("1");
        //查询所有合格的tbitem
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        long start = System.currentTimeMillis();
        System.out.println("查询开始。。。");
        //遍历循环
        for (TbItem tbItem : tbItems) {
            System.out.println(tbItem.getId()+"==="+tbItem.getTitle());
            Map map = JSON.parseObject(tbItem.getSpec(), Map.class);
            tbItem.setSpecMap(map);
        }
        System.out.println("查询结束。。。");
        long end = System.currentTimeMillis();
        System.out.println("查询花费时间："+(end-start)/1000+"s");
        //打入solr索引库

        System.out.println("solr开始。。。");
        solrTemplate.saveBeans(tbItems);
        solrTemplate.commit();
        System.out.println("solr结束。。。");

    }

    public static void main(String[] args) {
        //加*可以搜索jar包下的配置文件
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
        SolrUtil solrUtil = (SolrUtil) context.getBean("solrUtil");
        solrUtil.importData();
    }
}
