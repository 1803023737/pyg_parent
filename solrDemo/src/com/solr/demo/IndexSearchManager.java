package com.solr.demo;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class IndexSearchManager {

    
    @Test
    public void search() throws SolrServerException {

        //创建solr server  链接solr服务器
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8580/solr/collection2");
        //创建查询对象
        SolrQuery query = new SolrQuery();
        //设置查询对象
        //query.setQuery("product_name:小黄");
        query.setQuery("id:1");
        QueryResponse queryResponse = solrServer.query(query);
        //获得所有结果
        SolrDocumentList results = queryResponse.getResults();
        //结果总数
        long numFound = results.getNumFound();
        System.out.println("获得结果总数："+numFound);
        for (SolrDocument document : results) {
            System.out.println(document.get("id"));
            //System.out.println(document.get("book_name"));
            System.out.println(document.get("product_name"));
            System.out.println(document.get("product_catalog"));
            System.out.println(document.get("product_price"));
            System.out.println(document.get("product_picture"));
            System.out.println("=============================");
        }
    }

    @Test
    public void searchComplex() throws SolrServerException {

        //创建solr server  链接solr服务器
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8580/solr");
        //创建查询对象
        SolrQuery query = new SolrQuery();
        //设置查询对象
        query.setQuery("小黄");
        //query.setQuery("product_keyword:小黄");
        //query.set("q","product_keyword:小黄");
        //过滤条件  可以设置多个  但是要用add方法
        query.setFilterQueries("product_price:[1 TO 100]");
        //多个过滤条件
        //query.addFilterQuery("product_price:[1 TO 100]");
        //设置排序
        query.setSort("product_price", SolrQuery.ORDER.desc);
        //设置分页信息
        query.setStart(0);
        query.setRows(10);
        //设置返回的field集合
        query.setFields("id,product_name,product_price");
        //设置默认域
        query.set("df", "product_keyword");
        //设置高亮信息
        query.setHighlight(true);
        query.addHighlightField("product_name");//设置高亮列
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");

        QueryResponse queryResponse = solrServer.query(query);
        //获得所有结果
        SolrDocumentList results = queryResponse.getResults();
        //结果总数
        long numFound = results.getNumFound();
        System.out.println("获得结果总数："+numFound);
        //高亮集合
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument document : results) {
            System.out.println(document.get("id"));
            List<String> list = highlighting.get(document.get("id")).get("product_name");
            if(list!=null){//集合不为空
                System.out.println("高亮显示的name:"+list.get(0));
            }else{
                System.out.println(document.get("product_name"));
            }
            System.out.println(document.get("product_name"));
            System.out.println(document.get("product_catalog"));
            System.out.println(document.get("product_price"));
            System.out.println(document.get("product_picture"));
            System.out.println("=============================");
        }
    }

    @Test
    public void search2() throws SolrServerException {

        SolrServer solrServer=new HttpSolrServer("http://localhost:8580//solr");
        SolrQuery query=new SolrQuery("id:1");
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList results = queryResponse.getResults();
        for (SolrDocument document : results) {
            System.out.println(document.get("product_name"));
        }
    }



}
