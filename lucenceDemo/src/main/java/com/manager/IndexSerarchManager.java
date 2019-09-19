package com.manager;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//搜索流程
public class IndexSerarchManager {

    //封装查询方法
    public void dosearch(Query query){

        try {
            File indexFile=new File("D:\\luceneindex\\l1\\");//索引库地址
            Directory dictionary= FSDirectory.open(indexFile);
            IndexReader reader= DirectoryReader.open(dictionary);

            IndexSearcher indexSearcher=new IndexSearcher(reader);
            //通过searcher搜索  第二个参数 显示头部的头几条
            TopDocs topDocs = null;
            topDocs = indexSearcher.search(query, 10);
            //获得结果
            int totalHits = topDocs.totalHits;//总条数
            System.out.println("记录总数："+totalHits);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            //所有打分
            for (ScoreDoc scoreDoc : scoreDocs) {
                //打印doc 的id和doc的分数
                //System.out.println(scoreDoc.doc+"==="+scoreDoc.score);
                //获得docid 获得document
                int docId = scoreDoc.doc;
                Document doc = indexSearcher.doc(docId);
                System.out.println(doc.get("id"));
                System.out.println(doc.get("name"));
                System.out.println(doc.get("price"));
                System.out.println(doc.get("pic"));
                System.out.println(doc.get("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //根据条件搜索
    @Test
    public void indexSearch() {

        //使用queryParser  需要制定分词器 分析器必须与索引分词一致  因为分词器不同 搜索词就可能与索引不匹配
        //创建query对象 默认搜索域的名称
        QueryParser queryParser=new QueryParser("description",new IKAnalyzer());
        try {
            Query query = queryParser.parse("name:python");
            dosearch(query);

            //File indexFile=new File("D:\\luceneindex\\l1\\");//索引库地址
            //Directory dictionary= FSDirectory.open(indexFile);
            //IndexReader reader= DirectoryReader.open(dictionary);
            //
            //IndexSearcher indexSearcher=new IndexSearcher(reader);
            ////通过searcher搜索  第二个参数 显示头部的头几条
            //TopDocs topDocs = indexSearcher.search(query, 10);
            ////获得结果
            //int totalHits = topDocs.totalHits;//总条数
            //System.out.println("记录总数："+totalHits);
            //ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            ////所有打分
            //for (ScoreDoc scoreDoc : scoreDocs) {
            //    //打印doc 的id和doc的分数
            //    //System.out.println(scoreDoc.doc+"==="+scoreDoc.score);
            //    //获得docid 获得document
            //    int docId = scoreDoc.doc;
            //    Document doc = indexSearcher.doc(docId);
            //    System.out.println(doc.get("id"));
            //    System.out.println(doc.get("name"));
            //    System.out.println(doc.get("price"));
            //    System.out.println(doc.get("pic"));
            //    System.out.println(doc.get("description"));
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //精确查询  termQuery
    @Test
    public void termQuery(){
        //termquery查询
        Query termQuery = new TermQuery(new Term("description", "语言"));
        dosearch(termQuery);
    }


    @Test
    public void numericRangeQuery(){
        NumericRangeQuery<Float> query = NumericRangeQuery.newFloatRange("price", 50.0f, 100.0f, false, false);
        dosearch(query);
    }

        //组合查询
    @Test
    public void booleanQuery(){

        BooleanQuery query = new BooleanQuery();
        Query termQuery = new TermQuery(new Term("name", "java"));
        NumericRangeQuery<Float> numericRangeQuery = NumericRangeQuery.newFloatRange("price", 50.0f, 100.0f, true, true);
        //must 和should  should失效   should必须和should
        query.add(termQuery, BooleanClause.Occur.SHOULD);//should 和must  should失去意义！  条件实效，如同不存在！
        query.add(numericRangeQuery, BooleanClause.Occur.MUST);
        System.out.println("打印关系表达式："+query);
        dosearch(query);
    }

    //multiFieldQuery
    @Test
    public void multiFieldQuery(){
        //查询域名数组
        String [] fields={"name","description"};
        //查询parser
        Map<String, Float> boots=new HashMap<>();
        //在搜索时 设置boost值  看查询顺序排序  在多个条件查询情况下 设置查询条件的顺序 的boost值
        boots.put("description", 100f);
        MultiFieldQueryParser parser=new MultiFieldQueryParser(fields,new StandardAnalyzer(),boots);
        try {
            //Query query = parser.parse("java");
            //Query query = parser.parse("name:java AND description:java");
            //Query query = parser.parse("name:java OR description:java");
            Query query = parser.parse("java");
            System.out.println("打印关系表达式："+query);
            dosearch(query);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    //删除文档---根据索引
    //根据条件删除   增删改都要indexwriter
    @Test
    public void deleteIndex() throws IOException {
        // 分词器 加上lucene版本
        IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_4_10_3,new StandardAnalyzer());

        String filepath="D:\\luceneindex\\l1\\";
        File indexFile=new File(filepath);
        Directory directory=FSDirectory.open(indexFile);
        //生成indexwriter对象
        IndexWriter indexWriter=new IndexWriter(directory,indexWriterConfig);
        //term 是索引域中最小的单位  删除索引呀
        // 删除索引的前提是  你这个field是保存时建立索引的！ 没有索引怎么删？？？！  底层应该是查找后再删除！
        indexWriter.deleteDocuments(new Term("id","2"));//两个参数  第一个是索引域的name 后面是索引域的值
        //关闭  必须关闭流 不然上面执行删除不成功
        indexWriter.close();
    }

    //删除全部
    @Test
    public void deleteAllIndex() throws IOException {
        // 分词器 加上lucene版本
        IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_4_10_3,new StandardAnalyzer());

        String filepath="D:\\luceneindex\\l1\\";
        File indexFile=new File(filepath);
        Directory directory=FSDirectory.open(indexFile);
        //生成indexwriter对象
        IndexWriter indexWriter=new IndexWriter(directory,indexWriterConfig);
        //term 是索引域中最小的单位  删除索引呀
        // 删除索引的前提是  你这个field是保存时建立索引的！ 没有索引怎么删？？？！  底层应该是查找后再删除！
        //indexWriter.deleteDocuments(new Term("id","2"));//两个参数  第一个是索引域的name 后面是索引域的值
        indexWriter.deleteAll();
        //关闭  必须关闭流 不然上面执行删除不成功
        indexWriter.close();
    }


    //更新索引
    @Test
    public void updateIndex() throws IOException{
        // 分词器 加上lucene版本
        IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_4_10_3,new StandardAnalyzer());

        String filepath="D:\\luceneindex\\l1\\";
        File indexFile=new File(filepath);
        Directory directory=FSDirectory.open(indexFile);
        //生成indexwriter对象
        IndexWriter indexWriter=new IndexWriter(directory,indexWriterConfig);

        //更新  将新对象    底层： 修改》》删除》》添加
        Document document=new Document();
        document.add(new TextField("name","python science", Field.Store.YES));
        indexWriter.updateDocument(new Term("name","python"), document);
        indexWriter.close();
    }





}
