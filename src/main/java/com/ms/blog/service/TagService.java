package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.PageParam;
import com.ms.blog.common.Result;
import com.ms.blog.entity.param.TagParam;
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

    /**
     *  获取所有标签列表
     * @param pageParam 分页参数
     * @return 查找结果
     */
    Result<PageData<TagVo>> getTagList(PageParam pageParam);

    /**
     * 删除标签
     * @param id    标签id
     * @return  结果
     */
    Result<Integer> deleteTag(Integer id);

    /**
     * 新增标签
     * @param tagParam  标签参数类
     * @return  结果
     */
    Result<Integer> insertTag(TagParam tagParam);

    /**
     * 修改标签信息
     * @param tagParam 标签参数类
     * @return  结果
     */
    Result<Integer> updateTag(TagParam tagParam);

    /**
     * 获取热门标签
     * @return  热门标签列表
     */
    Result<List<TagVo>> getPopularTagList();

}
