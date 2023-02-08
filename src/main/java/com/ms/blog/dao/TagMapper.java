package com.ms.blog.dao;

import com.ms.blog.entity.vo.TagVo;
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
    List<TagVo> getTagListByArticleId(Integer articleId);
}
