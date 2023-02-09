package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Tag;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签接口
 * @author wyh
 * @date 2023/02/01 17:37
 */
@Mapper
public interface TagMapper {

    /**
     * 根据用户id查询所属标签列表
     * @param articleId 文章id
     * @return 标签列表
     */
    List<Tag> getTagListByArticleId(Integer articleId);

    /**
     * 通过标签名获取标签id
     * @param TagName 表签名
     * @return  标签类
     */
    int getTagByName(String TagName);

    /**
     * 获取所有标签
     * @param tagPage 分页参数
     * @return 组件
     */
    IPage<Tag> getTagList(Page<Tag> tagPage);

    /**
     * 通过id删除标签
     * @param id 标签id
     * @return 结果
     */
    int deleteTag(Integer id);

    /**
     * 新增标签
     * @param tag 标签类
     * @return 结果
     */
    int insertTag(Tag tag);

    /**
     * 获取热门（所有）标签
     * @return 结果
     */
    List<Tag> getPopularTagList();

    /**
     *  通过标签id获取标签
     * @param id 标签id
     * @return 标签
     */
    Tag getTagById(Integer id);

    /**
     * 更新标签点击量
     * @param TagId 标签id
     * @param clickVolume  点击量
     * @return 结果
     */
    int updateTagClickVolume(Integer TagId, Integer clickVolume);
}
