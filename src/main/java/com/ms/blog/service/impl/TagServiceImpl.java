package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.TagMapper;
import com.ms.blog.entity.Tag;
import com.ms.blog.entity.param.TagParam;
import com.ms.blog.entity.vo.TagVo;
import com.ms.blog.service.TagService;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 标签类接口实现
 * @author wyh
 * @date 2023/01/31 17:08
 */
@Service
 public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String HOT_TAG_LIST = "HOT_TAG_LIST_";

    @Override
    @ServiceLog("通过标签名获取标签id")
    public Result<Integer> getTagByName(String tagName) {
        Integer id = tagMapper.getTagByName(tagName);
        return ResultUtils.success(id);
    }

    @Override
    @ServiceLog("根据用户id查询所属标签列表")
    public Result<List<TagVo>> getTagListByArticleId(Integer articleId) {
        List<Integer> tagIdList = tagMapper.getTagIdListByArticleId(articleId);
        List<Tag> tagList  =new ArrayList<>();

        for (Integer i : tagIdList) {
            tagList.add(tagMapper.getTagById(i));
        }

        List<TagVo> tagVoList = new ArrayList<>();
        TagVo tagVo = new TagVo();
        for (Tag t : tagList) {
            BeanUtils.copyProperties(t, tagVo);
            tagVoList.add(tagVo);
        }
        return ResultUtils.success(tagVoList);
    }

    @Override
    @ServiceLog("获取所有标签")
    public Result<PageData<TagVo>> getTagList(PageParam pageParam) {
        Page<Tag> tagPage = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        IPage<Tag> tagIPage = tagMapper.getTagList(tagPage);
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag t : tagIPage.getRecords()) {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(t, tagVo);
            tagVoList.add(tagVo);
        }
        PageData<TagVo> tagVoPageData = new PageData<>(tagVoList, tagIPage.getTotal(), tagIPage.getPages(), tagIPage.getCurrent());
        return ResultUtils.success(tagVoPageData);
    }

    @Override
    @ServiceLog("通过标签id删除标签")
    public Result<Integer> deleteTag(Integer id) {
        tagMapper.deleteTag(id);
        tagMapper.deleteArticleTag(id);
        return ResultUtils.success();
    }

    @Override
    @ServiceLog("新增标签")
    public Result<Integer> insertTag(TagParam tagParam) {
        Tag tag = Tag.builder()
                .clickVolume(0)
                .name(tagParam.getName())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        return ResultUtils.success(tagMapper.insertTag(tag));
    }

    @Override
    @ServiceLog("获取热门标签")
    public Result<List<TagVo>> getPopularTagList() {
        return ResultUtils.success(JSON.parseArray(redisTemplate.opsForValue().get(HOT_TAG_LIST), TagVo.class));
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.getTagById(id);
    }

}
