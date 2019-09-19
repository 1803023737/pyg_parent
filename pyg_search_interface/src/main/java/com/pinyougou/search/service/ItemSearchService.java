package com.pinyougou.search.service;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {

    //返回信息  除了sku商品  还有过滤搜索条件等信息
    public Map findItemList(Map searchMap);

    public void importList(List list);

    public void deleteByGoodsIds(List goodsIds);


}
