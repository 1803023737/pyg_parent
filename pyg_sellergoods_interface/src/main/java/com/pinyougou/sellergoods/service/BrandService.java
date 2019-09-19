package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<TbBrand> getAll();

    public PageResult findPage(int page,int pageSize);

    public void add(TbBrand brand);

    public TbBrand selectOne(Long id);

    public void update(TbBrand brand);


    public void delete(Long[] ids);

    public PageResult findPage(TbBrand brand, int page, int pageSize);



    public List<Map> selectOptionList();
}
