package com.pinyougou.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.mapper.TbContentMapper;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentExample;
import com.pinyougou.pojo.TbContentExample.Criteria;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbContent> findAll() {
        return contentMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbContent content) {
        contentMapper.insert(content);

        //新增后清除之前的缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
    }

    /**
     * 修改
     */
    @Override
    public void update(TbContent content) {
        //查询原来的分组id
        TbContent oldtbContent = contentMapper.selectByPrimaryKey(content.getId());
        Long categoryId = oldtbContent.getCategoryId();
        //清除之前的缓
        redisTemplate.boundHashOps("content").delete(categoryId);
        contentMapper.updateByPrimaryKey(content);
        //更新完清除现在分类id下的
        if (categoryId.longValue() != content.getCategoryId().longValue()) {//判断前后分类id是否一致  是否修改了
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        }
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbContent findOne(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            //清除之前的缓存
            TbContent oldtbContent = contentMapper.selectByPrimaryKey(id);
            Long categoryId = oldtbContent.getCategoryId();
            redisTemplate.boundHashOps("content").delete(categoryId);
            //数据库删除
            contentMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(TbContent content, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();

        if (content != null) {
            if (content.getTitle() != null && content.getTitle().length() > 0) {
                criteria.andTitleLike("%" + content.getTitle() + "%");
            }
            if (content.getUrl() != null && content.getUrl().length() > 0) {
                criteria.andUrlLike("%" + content.getUrl() + "%");
            }
            if (content.getPic() != null && content.getPic().length() > 0) {
                criteria.andPicLike("%" + content.getPic() + "%");
            }
            if (content.getStatus() != null && content.getStatus().length() > 0) {
                criteria.andStatusLike("%" + content.getStatus() + "%");
            }

        }

        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<TbContent> findListByContentCategoryId(Long id) {

        //使用redis缓存
        List<TbContent> list = (List<TbContent>) redisTemplate.boundHashOps("content").get(id);
        if (list == null) {
            System.out.println("从数据库查询数据放入缓存");
            //条件
            TbContentExample example = new TbContentExample();
            example.createCriteria().andCategoryIdEqualTo(id).andStatusEqualTo("1");
            //排序
            example.setOrderByClause("sort_order");
            list = contentMapper.selectByExample(example);
            redisTemplate.boundHashOps("content").put(id, list);//放入缓存
        } else {
            //条件
            System.out.println("从缓存中读取数据！");
        }
        return list;
    }

}
