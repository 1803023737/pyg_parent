package com.manager;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.pojo.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建索引类
 */
public class IndexManager {

    //创建索引库 文本域
    @Test
    public void createIndex() {
        //采集数据 数据库
        BookDao bookDao = new BookDaoImpl();
        try {
            List<Book> books = bookDao.queryBookList();
            List<Document> documentList = new ArrayList<>();
            //封装document  一条数据库记录记录到一个document中
            Document doc;
            for (Book book : books) {
                doc=new Document();
                //不分词，索引，存储
                Field id=new StringField("id",book.getId().toString(), Field.Store.YES);//store与索引无关  如果store为no 可能会出现索引有，document无  store只决定文档域是否存在 不能决定索引域是否存在
                //分词，索引，存储
                Field name = new TextField("name", book.getName(), Field.Store.YES);
                //分词，索引，存储  但是是数字类型
                Field price = new FloatField("price", book.getPrice(), Field.Store.YES);
                //不分词，不索引，存储
                Field pic = new StoredField("pic", book.getPic());
                //分词，索引，不存储
                Field desc = new TextField("description",	book.getDescription(), Field.Store.NO);

                //设置加权值  boost值
                if(book.getId().toString().equals("2")){
                    desc.setBoost(100f);//加权
                }

                // 把域（Field）添加到文档（Document）中
                doc.add(id);
                doc.add(name);
                doc.add(price);
                doc.add(pic);
                doc.add(desc);
                documentList.add(doc);
            }

            //创建分词器
            //Analyzer analyzer=new StandardAnalyzer();//4.10.3版本可以空参构造
            //中文分词器
            Analyzer analyzer = new IKAnalyzer();
            //创建indexwriter
            IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);

            File indexFile=new File("D:\\luceneindex\\l1\\");//索引库地址
            FSDirectory dictionary= FSDirectory.open(indexFile);
            IndexWriter indexWriter=new IndexWriter(dictionary,conf);

            //将document写入索引库地址
            for (Document document : documentList) {
                //遍历  循环
                indexWriter.addDocument(document);
            }
            //关闭writer
            indexWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
