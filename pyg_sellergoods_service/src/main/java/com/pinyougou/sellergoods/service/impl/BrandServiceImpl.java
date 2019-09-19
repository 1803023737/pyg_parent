package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//事务注解
@Transactional
//暴露服务
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> getAll() {

        TbBrandExample example = new TbBrandExample();
        return tbBrandMapper.selectByExample(example);
    }

    /**
     * @param page     页码数
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public PageResult findPage(int page, int pageSize) {

        //mybatis 分页插件  设置分页参数
        PageHelper.startPage(page, pageSize);
        Page<TbBrand> p = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        //封装到自定义pageresult类上
        PageResult pageResult = new PageResult();
        pageResult.setRows(p.getResult());
        pageResult.setTotal(p.getTotal());
        return pageResult;
    }

    @Override
    public void add(TbBrand brand) {
        tbBrandMapper.insert(brand);
        //测试事务！！！
        //int i=1/0;
    }

    @Override
    public TbBrand selectOne(Long id) {

        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        //根据主键更新
        System.out.println(brand.getId() + brand.getFirstChar() + brand.getName());
        tbBrandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(TbBrand brand, int page, int pageSize) {

       // System.out.println(brand.getId()+brand.getName()+brand.getFirstChar());
        //mybatis 分页插件  设置分页参数
        PageHelper.startPage(page, pageSize);
        TbBrandExample example = new TbBrandExample();
        if (brand != null) {
            TbBrandExample.Criteria criteria=example.createCriteria();
            if (brand.getName() != null && brand.getName().length() > 0) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
                criteria.andFirstCharLike("%" + brand.getFirstChar() + "%");
            }
        }
        Page<TbBrand> p = (Page<TbBrand>) tbBrandMapper.selectByExample(example);
        //封装到自定义pageresult类上
        PageResult pageResult = new PageResult();
        pageResult.setRows(p.getResult());
        pageResult.setTotal(p.getTotal());
        return pageResult;

    }
    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }
}
