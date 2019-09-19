package com.pinyougou.page.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.page.service.ItemPageService;
import com.pinyougou.pojo.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemPageServiceImpl implements ItemPageService {

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Value("${pagedir}")
    private String pagedir;

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public boolean genItemHtml(Long goodsId) {

        //获得配置
        Configuration configuration = freeMarkerConfig.getConfiguration();
        try {
            //加载模板
            Template template = configuration.getTemplate("item.ftl");
            //数据模型 从数据库获得数据
            Map modelMap = new HashMap();
            //1.从数据库获得数据
            TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(goodsId);
            modelMap.put("goods", tbGoods);
            //2.详细表数据
            TbGoodsDesc tbGoodsDesc = tbGoodsDescMapper.selectByPrimaryKey(goodsId);
            modelMap.put("goodsdesc", tbGoodsDesc);
            //3.商品分类
            TbItemCat tbItemCat1 = tbItemCatMapper.selectByPrimaryKey(tbGoods.getCategory1Id());
            TbItemCat tbItemCat2 =tbItemCatMapper.selectByPrimaryKey(tbGoods.getCategory2Id());
            TbItemCat tbItemCat3 =tbItemCatMapper.selectByPrimaryKey(tbGoods.getCategory3Id());
            //分类数据放大数据模型中
            modelMap.put("tbItemCat1", tbItemCat1);
            modelMap.put("tbItemCat2", tbItemCat2);
            modelMap.put("tbItemCat3", tbItemCat3);
            //4.读取sku列表数据
            TbItemExample example=new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria().andGoodsIdEqualTo(goodsId).andStatusEqualTo("1");//状态有效  spuid
            example.setOrderByClause("is_default desc");//是否默认 desc
            List<TbItem> tbItems = tbItemMapper.selectByExample(example);
            modelMap.put("tbItems", tbItems);
            //输出流
            FileWriter fileWriter = new FileWriter(new File(pagedir + goodsId + ".html"));
            //输出文件
            template.process(modelMap, fileWriter);
            //关闭流
            fileWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
