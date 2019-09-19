package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.page.service.ItemPageService;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojogroup.Goods;
import com.pinyougou.search.service.ItemSearchService;
import com.pinyougou.sellergoods.service.GoodsService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * .controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbGoods> findAll() {
        return goodsService.findAll();
    }

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return goodsService.findPage(page, rows);
    }

    /**
     * 增加
     * @param goods
     * @return
     */
    //@RequestMapping("/add")
    //public Result add(@RequestBody TbGoods goods){
    //	try {
    //		goodsService.add(goods);
    //		return new Result(true, "增加成功");
    //	} catch (Exception e) {
    //		e.printStackTrace();
    //		return new Result(false, "增加失败");
    //	}
    //}

    /**
     * 修改
     *
     * @param goods
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Goods goods) {
        try {
            goodsService.update(goods);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbGoods findOne(Long id) {
        return goodsService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            goodsService.delete(ids);
            //从索引库中删除
            itemSearchService.deleteByGoodsIds(Arrays.asList(ids));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbGoods goods, int page, int rows) {
        return goodsService.findPage(goods, page, rows);
    }

    //导入时间可能比较长
    @Reference(timeout = 100000)
    private ItemSearchService itemSearchService;

    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] ids, String status) {
        try {
            goodsService.updateStatus(ids, status);
            //更新审核通过商品到索引库中
            if (status.equals("1")) {//审核通过
                //1.1更新索引库  组合调用
                List<TbItem> itemListByGoodsIdAndStatus = goodsService.findItemListByGoodsIdAndStatus(ids, status);
                System.out.println("获得审核的list"+itemListByGoodsIdAndStatus);
                //1.2导入数据  更新索引
                if(itemListByGoodsIdAndStatus!=null && itemListByGoodsIdAndStatus.size()>0){
                    itemSearchService.importList(itemListByGoodsIdAndStatus);
                    System.out.println("导入索引库"+itemListByGoodsIdAndStatus);
                }
                //1.3生成商品详细页
                for (Long goodsId : ids) {//循环生成页面
                boolean b = itemPageService.genItemHtml(goodsId);
                }
            }
            return new Result(true, "成功");
        } catch (Exception e) {
            return new Result(false, "失败");
            //e.printStackTrace();
        }
    }

    @Reference(timeout = 40000)
    private ItemPageService itemPageService;

    //测试freemarker 商品详情页
    @RequestMapping("/genHtml")
        public void genHtml(Long goodsId){
        boolean b = itemPageService.genItemHtml(goodsId);
        System.out.println("生成商品详情页结果："+b);
    }


}
