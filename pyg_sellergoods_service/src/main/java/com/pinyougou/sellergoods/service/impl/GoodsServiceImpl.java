package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import com.pinyougou.pojo.TbGoodsExample.Criteria;
import com.pinyougou.pojogroup.Goods;
import com.pinyougou.sellergoods.service.GoodsService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TbGoodsMapper goodsMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbGoods> findAll() {
        return goodsMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Autowired
    private TbGoodsDescMapper goodsDescMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbBrandMapper brandMapper;

    @Autowired
    private TbSellerMapper sellerMapper;


    /**
     * 增加
     */
    @Override
    public void add(Goods goods) {

        //goodsMapper.insert(goods);
        //插入基本信息
        goods.getTbGoods().setAuditStatus("0");
        goodsMapper.insert(goods.getTbGoods());
        //插入详情表
        goods.getTbGoodsDesc().setGoodsId(goods.getTbGoods().getId());//外键关联
        goodsDescMapper.insert(goods.getTbGoodsDesc());//插入商品详情表数据

        //itemlist
        List<TbItem> tbItemList = goods.getTbItemList();
        //[{"spec":{"网络":"移动3G","机身内存":"16G"},"price":0,"num":9999,"status":"0","isDefault":"0"},
        //{"spec":{"网络":"移动4G","机身内存":"16G"},"price":0,"num":9999,"status":"0","isDefault":"0"},
        //{"spec":{"网络":"联通3G","机身内存":"16G"},"price":0,"num":9999,"status":"0","isDefault":"0"}]
        if ("1".equals(goods.getTbGoods().getIsEnableSpec())){
            //启用
            for (TbItem tbItem : tbItemList) {
                //部分字段  需要手动set
                String title = goods.getTbGoods().getGoodsName();
                Map<String, Object> map = JSONObject.parseObject(tbItem.getSpec());
                for (String key : map.keySet()) {
                    title += " " + map.get(key);
                }
                tbItem.setTitle(title);
                //设置通用属性
                setItemValues(tbItem,goods);
                tbItemMapper.insert(tbItem);
            }
        }else {
            //未启用
            //TbItem tbItem=new TbItem();
            //tbItem.setTitle(goods.getTbGoods().getGoodsName());
            //tbItem.setPrice(goods.getTbGoods().getPrice());
            //tbItem.setNum(9999);
            //tbItem.setStatus("1");
            //tbItem.setIsDefault("1");
            //tbItem.setSpec("{}");
            ////设置通用属性
            //setItemValues(tbItem,goods);
            //tbItemMapper.insert(tbItem);
            saveItemList(goods);
        }
    }

    private void setItemValues( TbItem tbItem,Goods goods){
        //商品分类
        tbItem.setCategoryid(goods.getTbGoods().getCategory3Id());
        //创建日期
        tbItem.setCreateTime(new Date());
        //更新日期
        tbItem.setUpdateTime(new Date());
        //goodsid
        tbItem.setGoodsId(goods.getTbGoods().getId());
        //sellerid
        tbItem.setSellerId(goods.getTbGoods().getSellerId());
        //分类名称
        tbItem.setCategory(tbItemCatMapper.selectByPrimaryKey(goods.getTbGoods().getCategory3Id()).getName());
        //品牌名称
        TbBrand tbBrand = brandMapper.selectByPrimaryKey(goods.getTbGoods().getBrandId());
        tbItem.setBrand(tbBrand.getName());
        //商家名称
        TbSeller tbSeller = sellerMapper.selectByPrimaryKey(goods.getTbGoods().getSellerId());
        tbItem.setSeller(tbSeller.getNickName());
    }


    /**
     * 修改
     */
    @Override
    public void update(Goods goods) {
        //goods 更新基本表
        goodsMapper.updateByPrimaryKey(goods.getTbGoods());
        //goodesc  更新扩展表
        goodsDescMapper.updateByPrimaryKey(goods.getTbGoodsDesc());
        //items表更新  先删除 在新增
        //删除 原有sku
        TbItemExample tbItemExample=new TbItemExample();
        tbItemExample.createCriteria().andGoodsIdEqualTo(goods.getTbGoods().getId());
        tbItemMapper.deleteByExample(tbItemExample);
        //插入新的sku列表
        List<TbItem> tbItemList = goods.getTbItemList();
        //[{"spec":{"网络":"移动3G","机身内存":"16G"},"price":0,"num":9999,"status":"0","isDefault":"0"},
        //{"spec":{"网络":"移动4G","机身内存":"16G"},"price":0,"num":9999,"status":"0","isDefault":"0"},
        //{"spec":{"网络":"联通3G","机身内存":"16G"},"price":0,"num":9999,"status":"0","isDefault":"0"}]
        if ("1".equals(goods.getTbGoods().getIsEnableSpec())){
            //启用
            for (TbItem tbItem : tbItemList) {
                //部分字段  需要手动set
                String title = goods.getTbGoods().getGoodsName();
                Map<String, Object> map = JSONObject.parseObject(tbItem.getSpec());
                for (String key : map.keySet()) {
                    title += " " + map.get(key);
                }
                tbItem.setTitle(title);
                //设置通用属性
                setItemValues(tbItem,goods);
                tbItemMapper.insert(tbItem);
            }
        }else {
            //未启用
            //TbItem tbItem=new TbItem();
            //tbItem.setTitle(goods.getTbGoods().getGoodsName());
            //tbItem.setPrice(goods.getTbGoods().getPrice());
            //tbItem.setNum(9999);
            //tbItem.setStatus("1");
            //tbItem.setIsDefault("1");
            //tbItem.setSpec("{}");
            ////设置通用属性
            //setItemValues(tbItem,goods);
            //tbItemMapper.insert(tbItem);
            saveItemList(goods);
        }
    }

    private void saveItemList(Goods goods){
        //未启用
        TbItem tbItem=new TbItem();
        tbItem.setTitle(goods.getTbGoods().getGoodsName());
        tbItem.setPrice(goods.getTbGoods().getPrice());
        tbItem.setNum(9999);
        tbItem.setStatus("1");
        tbItem.setIsDefault("1");
        tbItem.setSpec("{}");
        //设置通用属性
        setItemValues(tbItem,goods);
        tbItemMapper.insert(tbItem);
    }


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbGoods findOne(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            //goodsMapper.deleteByPrimaryKey(id);

            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
            tbGoods.setIsDelete("1");//逻辑删除
            goodsMapper.updateByPrimaryKey(tbGoods);
        }
    }

    @Override
    public PageResult findPage(TbGoods goods, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbGoodsExample example = new TbGoodsExample();
        Criteria criteria = example.createCriteria();
        //指定未为逻辑删除   统一处理
        criteria.andIsDeleteIsNull();
        if (goods != null) {
            if (goods.getSellerId() != null && goods.getSellerId().length() > 0) {
                //criteria.andSellerIdLike("%" + goods.getSellerId() + "%");
                criteria.andSellerIdEqualTo(goods.getSellerId());
            }
            if (goods.getGoodsName() != null && goods.getGoodsName().length() > 0) {
                criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
            }
            if (goods.getAuditStatus() != null && goods.getAuditStatus().length() > 0) {
                criteria.andAuditStatusLike("%" + goods.getAuditStatus() + "%");
            }
            if (goods.getIsMarketable() != null && goods.getIsMarketable().length() > 0) {
                criteria.andIsMarketableLike("%" + goods.getIsMarketable() + "%");
            }
            if (goods.getCaption() != null && goods.getCaption().length() > 0) {
                criteria.andCaptionLike("%" + goods.getCaption() + "%");
            }
            if (goods.getSmallPic() != null && goods.getSmallPic().length() > 0) {
                criteria.andSmallPicLike("%" + goods.getSmallPic() + "%");
            }
            if (goods.getIsEnableSpec() != null && goods.getIsEnableSpec().length() > 0) {
                criteria.andIsEnableSpecLike("%" + goods.getIsEnableSpec() + "%");
            }
            //if (goods.getIsDelete() != null && goods.getIsDelete().length() > 0) {
            //    criteria.andIsDeleteLike("%" + goods.getIsDelete() + "%");
            //}

        }

        Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Goods findOneZDY(Long id) {
        Goods goods=new Goods();
        //三部分
        TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
        goods.setTbGoods(tbGoods);
        TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
        goods.setTbGoodsDesc(tbGoodsDesc);
        //itemList
        //条件查询
        TbItemExample example=new TbItemExample();
        example.createCriteria().andGoodsIdEqualTo(id);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        goods.setTbItemList(tbItems);
        return goods;
    }

    @Override
    public void updateStatus(Long[] ids, String status) {
        //批量修改状态
        for (Long id : ids) {
            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
            tbGoods.setAuditStatus(status);
            goodsMapper.updateByPrimaryKey(tbGoods);
        }

    }

    /**
     * 根据spu的id集合查询sku列表
     * @param goodsIds
     * @param status
     * @return
     */
    public List<TbItem> findItemListByGoodsIdAndStatus(Long [] goodsIds,String status){

        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);//已审核  status=1
        System.out.println("status:"+status);
        List<Long> longs = Arrays.asList(goodsIds);
        criteria.andGoodsIdIn(longs);
        System.out.println("longd:"+longs);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        return tbItems;
    }



}
