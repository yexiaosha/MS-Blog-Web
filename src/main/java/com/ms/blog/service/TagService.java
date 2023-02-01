package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.vo.TagVo;
import java.util.List;

/**
 * 标签业务接口
 * @author wyh
 * @date 2023/01/31 17:07
 */
public interface TagService {

    /**
     * 通过标签名获取标签id
     * @param tagName 标签名
     * @return 通用结果
     */
    Result<TagVo> getTagIdByName(String tagName);

    /**
     * 根据用户id查询所属标签列表
     * @param articleId 文章id
     * @return 标签列表
     */
    Result<List<TagVo>> getTagListByArticleId(Integer articleId);

}
