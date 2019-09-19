package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Map findItemList(Map searchMap) {
        String keywords = (String) searchMap.get("keywords");
        searchMap.put("keywords", keywords.trim());
        Map resultMap = new HashMap();
        //1.搜索  输入框搜索
        Map searchListMap = searchList(searchMap);
        //2. 分组查询商品分类列表
        List<String> list = searchGroupList(searchMap);
        resultMap.put("category_list", list);
        //查询品牌和规格
        //先判断是否有类别
        if (!"".equals(searchMap.get("category"))) {
            System.out.println("已经选择了分类了哦！" + searchMap.get("category"));
            Map map = searchBrandAndSpecList((String) searchMap.get("category"));
            resultMap.putAll(map);
        } else {
            if (list.size() > 0) { //保证有商品分类在查询品牌和规格
                Map map = searchBrandAndSpecList(list.get(0));
                resultMap.putAll(map);
            }
        }

        //追加到
        resultMap.putAll(searchListMap);
        System.out.println("resultMap:" + resultMap);
        return resultMap;
    }



    //拆分各个方法

    //输入框查询   传入参数和返回值  都用map  后面需求扩展 可以只改内部代码就好
    private Map searchList(Map searchMap) {
        //返回值
        Map resultMap = new HashMap();

/*        Query query=new SimpleQuery("*:*");
        //关键字
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        //增加条件
        query.addCriteria(criteria);
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);*/

        //高亮搜索
        HighlightQuery query = new SimpleHighlightQuery();
        //高亮显示就是通过找到字段  然后加上前后标签  使其在页面高亮显示
        //高亮   1在哪一列加高亮显示  2前缀 3 后缀
        HighlightOptions highlightOptions = new HighlightOptions().addField("item_title").addField("item_brand");//高亮字段
        highlightOptions.setSimplePrefix("<em style='color:red'>");//前缀
        highlightOptions.setSimplePostfix("</em>");//后缀
        //设置高亮
        query.setHighlightOptions(highlightOptions);
        //关键字
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        //增加条件
        query.addCriteria(criteria);

        //System.out.println("category:"+searchMap.get("category"));
        //System.out.println(searchMap.get("category").equals(""));
        //1.2商品分类过滤
        if (!"".equals(searchMap.get("category"))) {//通过分类查询
            FilterQuery filterQuery = new SimpleFilterQuery();
            Criteria filterCriteria = new Criteria("item_category").is(searchMap.get("category"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }
        //1.3品牌过滤
        if (!"".equals(searchMap.get("brand"))) {//通过分类查询
            FilterQuery filterQuery = new SimpleFilterQuery();
            Criteria filterCriteria = new Criteria("item_brand").is(searchMap.get("brand"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }

        System.out.println("-----" + searchMap.get("spec"));
        //1.4规格过滤  循环过滤
        if (searchMap.get("spec") != null) {
            Map<String, String> specMap = (Map<String, String>) searchMap.get("spec");
            for (String key : specMap.keySet()) {
                FilterQuery filterQuery = new SimpleFilterQuery();
                Criteria filterCriteria = new Criteria("item_spec_" + key).is(specMap.get(key));
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }

        //1.5价格过滤
        if (!"".equals(searchMap.get("price"))) {//通过分类查询
            String price = (String) searchMap.get("price");
            String[] priceArr = price.split("-");
            if (!priceArr[0].equals("0")) {//最低价格不低于0
                FilterQuery filterQuery = new SimpleFilterQuery();
                //大于等于
                Criteria filterCriteria = new Criteria("item_price").greaterThanEqual(priceArr[0]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
            if (!priceArr[1].equals("*")) {//最高价格不等于*
                FilterQuery filterQuery = new SimpleFilterQuery();
                //小于等于
                Criteria filterCriteria = new Criteria("item_price").lessThanEqual(priceArr[1]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }

        //1.6 分页查询
        Integer pageNo = (Integer) searchMap.get("pageNo");//页码数
        if (pageNo == null) {
            pageNo = 1;
        }
        Integer pageSize = (Integer) searchMap.get("pageSize");//每页记录数
        if (pageSize == null) {
            pageSize = 20;
        }
        query.setOffset((pageNo - 1) * pageSize);//起始索引
        query.setRows(pageSize);//每页记录数

        //1.7排序查询
        String sortValue = (String) searchMap.get("sort");
        String sortField = (String) searchMap.get("sortField");
        if (sortValue != null && !sortValue.equals("")) {
            if ("asc".equals(sortValue)) {
                Sort sort = new Sort(Sort.Direction.ASC, "item_"+sortField);//按价格排序
                query.addSort(sort);
            }
            if ("desc".equals(sortValue)) {
                Sort sort = new Sort(Sort.Direction.DESC, "item_"+sortField);//按价格排序
                query.addSort(sort);
            }
        }
        //返回高亮页 对象
        HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);
        //返回高亮结果 高亮集合入口
        List<HighlightEntry<TbItem>> entryList = page.getHighlighted();
        for (HighlightEntry<TbItem> entry : entryList) {
            //高亮列的集合  可能设置多个高亮域
            List<HighlightEntry.Highlight> highlightList = entry.getHighlights();
            //for (HighlightEntry.Highlight highlight : highlightList) {
            //    List<String> snipplets = highlight.getSnipplets();//每个域可能有多值
            //    System.out.println(snipplets);
            //}

            //将高亮部分覆盖title
            if (highlightList.size() > 0 && highlightList.get(0).getSnipplets().size() > 0) {
                String highlight = highlightList.get(0).getSnipplets().get(0);
                TbItem item = entry.getEntity();
                item.setTitle(highlight);
            }
        }
        //结果封装
        resultMap.put("rows", page.getContent());
        resultMap.put("totalPages", page.getTotalPages());//总页数
        resultMap.put("total", page.getTotalElements());
        return resultMap;
    }

    private List<String> searchGroupList(Map searchMap) {

        List<String> nlist = new ArrayList();
        //查询对象
        Query query = new SimpleQuery("*:*");
        //根据关键词搜索
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        //设置分组选项
        GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");
        query.setGroupOptions(groupOptions);
        //查询
        GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
        //获得入口对象result
        GroupResult<TbItem> result = page.getGroupResult("item_category");
        //获得入口集合   对象list级别
        Page<GroupEntry<TbItem>> pagelist = result.getGroupEntries();
        //获得结果对象    对象list级别
        List<GroupEntry<TbItem>> list = pagelist.getContent();
        for (GroupEntry<TbItem> entry : list) {
            System.out.println();
            nlist.add(entry.getGroupValue());
        }
        return nlist;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    //根据商品类别查询品牌和规格
    private Map searchBrandAndSpecList(String category) {
        Map resultMap = new HashMap();
        //通过分类名称缓存搜索类型模板id
        Long typeTemplateId = (Long) redisTemplate.boundHashOps("itemCat").get(category);
        if (typeTemplateId != null) {
            //查询品牌了
            List brandList = (List) redisTemplate.boundHashOps("brandList").get(typeTemplateId);
            resultMap.put("brandList", brandList);
            //查询规格咯
            List specList = (List) redisTemplate.boundHashOps("specList").get(typeTemplateId);
            resultMap.put("specList", specList);
        }
        return resultMap;
    }

    @Override
    public void importList(List list) {

        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }
    @Override
    public void deleteByGoodsIds(List goodsIds){
        Query query=new SimpleQuery("*:*");
        Criteria criteria=new Criteria("item_goodsid").in(goodsIds);
        query.addCriteria(criteria);
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

}
