package com.test;

import com.pinyougou.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-solr.xml")
public class TestTemplateDemo {

    //引入魔板对象
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testAdd() {

        //实例对象
        TbItem tbItem=new TbItem();
        tbItem.setId(1l);
        tbItem.setTitle("华为mate10");
        tbItem.setCategory("手机");
        tbItem.setBrand("华为");
        tbItem.setSeller("华为旗舰店");
        tbItem.setPrice(new BigDecimal(1000));
        //保存对象
        solrTemplate.saveBean(tbItem);
        //提交
        solrTemplate.commit();
    }

    @Test
    public void findById() {

        TbItem tbItem = solrTemplate.getById(1l, TbItem.class);
        System.out.println(tbItem);
    }


    @Test
    public void deleteById() {
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }

    @Test
    public void testAddMany(){

        List beanlist=new ArrayList();
        for (int i = 0; i < 100; i++) {
            TbItem tbItem=new TbItem();
            tbItem.setId(1l+5000+i);
            tbItem.setTitle("华为mate10"+5000+i);
            tbItem.setCategory("手机"+4000+i);
            tbItem.setBrand("华为"+4000+i);
            tbItem.setSeller("华为旗舰店"+4000+i);
            tbItem.setPrice(new BigDecimal(1000));
            beanlist.add(tbItem);
        }
        //批量插入
        solrTemplate.saveBeans(beanlist);
        solrTemplate.commit();
    }


    @Test
    public void testPageQuery(){

        Query query=new SimpleQuery("*:*");
        //分页
        query.setOffset(20);//起始页
        query.setRows(20);//每页记录数

        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);
        List<TbItem> content = tbItems.getContent();
        for (TbItem tbItem : content) {
            System.out.println(tbItem.getId()+"====="+tbItem.getTitle());
        }
    }

    //条件查询
    @Test
    public void testPageQueryCondition(){

        Query query=new SimpleQuery("*:*");
        //分页
        query.setOffset(0);//起始页
        query.setRows(20);//每页记录数
        //条件
        Criteria criteria=new Criteria("item_category");
        criteria.contains("手机");//包含
        criteria=criteria.and("item_brand").is("华为400039");
        query.addCriteria(criteria);

        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);
        List<TbItem> content = tbItems.getContent();
        for (TbItem tbItem : content) {
            System.out.println(tbItem.getId()+"====="+tbItem.getTitle()+"==="+tbItem.getBrand());
        }
    }


    @Test
    public void deleteAll(){
        Query query=new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }




}
