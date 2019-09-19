package com.solr.demo;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class IndexManager {

    /**
     * 新增或者更新索引
     */
    @Test
    public void insertAndUpdateIndex() throws IOException, SolrServerException {

        //创建solr server  链接solr服务器
        HttpSolrServer solrServer=new HttpSolrServer("http://localhost:8580/solr");
        //创建document
        SolrInputDocument doc=new SolrInputDocument();
        //新增域
        doc.addField("id", "00144");
        doc.addField("name", "solr test！！！a");
        //doc.addField("name1", "solr test！！！");//必须先在schema.xml文件中创建索引字段
        //服务中新增doc
        solrServer.add(doc);
        //提交
        solrServer.commit();
    }

    //删除索引库
    @Test
    public void deleteIndexById() throws IOException, SolrServerException {

        //创建solr server  链接solr服务器
        HttpSolrServer solrServer=new HttpSolrServer("http://localhost:8580/solr");
        //根据指定id删除索引
        solrServer.deleteById("00144");
        //提交
        solrServer.commit();
    }

    //删除索引库
    @Test
    public void deleteIndex() throws IOException, SolrServerException {
        //创建solr server  链接solr服务器
        HttpSolrServer solrServer=new HttpSolrServer("http://localhost:8580/solr");
        //根据指定id删除索引
        // solrServer.deleteByQuery("id:001");
       solrServer.deleteByQuery("*:*");//删除所有
        //提交
        solrServer.commit();
    }
}
