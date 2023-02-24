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

    /**
     * 新增整合包相关链接
     * @param modPackRelatedLink 相关链接参数
     * @return 结果
     */
    int insertRelatedLink(ModPackRelatedLink modPackRelatedLink);

    /**
     * 删除整合包相关链接
     * @param id 相关链接id
     * @return 结果
     */
    int deleteRelatedLink(Integer id);

    /**
     *  更新整合包相关链接
     * @param modPackRelatedLink 整合包相关链接参数
     * @return 结果
     */
    int updateRelatedLink(ModPackRelatedLink modPackRelatedLink);
}
