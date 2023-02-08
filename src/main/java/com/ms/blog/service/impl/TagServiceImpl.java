package com.ms.blog.service.impl;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.dao.TagMapper;
import com.ms.blog.entity.param.TagParam;
import com.ms.blog.entity.vo.TagVo;
import com.ms.blog.service.TagService;
import java.util.List;
import javax.annotation.Resource;
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

    @Override
    public Result<TagVo> getTagIdByName(String tagName) {
        return null;
    }

    @Override
    public Result<List<TagVo>> getTagListByArticleId(Integer articleId) {
        return null;
    }

    @Override
    public Result<PageData<TagVo>> getTagList(PageParam pageParam) {
        return null;
    }

    @Override
    public Result<Integer> deleteTag(Integer id) {
        return null;
    }

    @Override
    public Result<Integer> insertTag(TagParam tagParam) {
        return null;
    }

    @Override
    public Result<Integer> updateTag(TagParam tagParam) {
        return null;
    }

    @Override
    public Result<List<TagVo>> getPopularTagList() {
        return null;
    }


}
