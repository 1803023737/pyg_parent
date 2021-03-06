package com.pinyougou.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("itemSearch")
public class ItemSearchController {

    //也可以在服务提供方配置   @service注解上面配置timeout
    @Reference(timeout = 5000)
    private ItemSearchService itemSearchService;

    @RequestMapping("search")
    public Map search(@RequestBody Map map){
        return itemSearchService.findItemList(map);
    }




}
