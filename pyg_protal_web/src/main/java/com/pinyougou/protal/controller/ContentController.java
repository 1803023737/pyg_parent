package com.pinyougou.protal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.pojo.TbContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    //注解式获得dubb哦服务
    @Reference
    private ContentService contentService;

    //轮播图的返回
    @RequestMapping("/findListByContentCategoryId")
    public List<TbContent> findListByContentCategoryId(Long id) {
        return contentService.findListByContentCategoryId(id);
    }

}
