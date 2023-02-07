package com.ms.blog.dao;

import com.ms.blog.entity.ModPackRelatedLink;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相关链接接口
 * @author wyh
 * @date 2023/02/07 17:42
 */
@Mapper
public interface ModPackRelatedLinkMapper {
    int insertRelatedLink(ModPackRelatedLink modPackRelatedLink);
    int deleteRelatedLink(Integer id);
    int updateRelatedLink(ModPackRelatedLink modPackRelatedLink);
}
