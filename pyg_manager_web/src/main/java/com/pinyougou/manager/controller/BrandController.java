package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    //json有转换器转换
    @RequestMapping("/findAll")
    public List<TbBrand> getall() {
        return brandService.getAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int page, int pageSize) {
        PageResult p = brandService.findPage(page, pageSize);
        return p;
    }

    @RequestMapping("/add")
    public Result insert(@RequestBody TbBrand brand) {
        Result json;
        try {
            brandService.add(brand);
            json = new Result(true, "新增成功！");
        } catch (Exception e) {
            json = new Result(false, "新增失败！");
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/findOne")
    public TbBrand selectOne(Long id) {
        return brandService.selectOne(id);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand) {
        Result resultJson = null;
        try {
            brandService.update(brand);
            resultJson = new Result(true, "更新成功！");
        } catch (Exception e) {
            resultJson = new Result(false, "更新失败！");
            e.printStackTrace();
        }
        return resultJson;
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        System.out.println(ids);
        Result resultJson = null;
        try {
            brandService.delete(ids);
            resultJson = new Result(true, "删除成功！");
        } catch (Exception e) {
            resultJson = new Result(false, "删除失败！");
            e.printStackTrace();
        }
        return resultJson;
    }

    @RequestMapping("/search")
    public PageResult findPageCondition(@RequestBody TbBrand brand, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int pageSize){
        PageResult page1 = brandService.findPage(brand, page, pageSize);
        return page1;
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
       return  brandService.selectOptionList();
    }

}
