package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.param.ModPackRelatedLinkParam;

/**
 * 整合包相关链接业务接口
 * @author wyh
 * @date 2023/02/07 15:13
 */
public interface ModPackRelatedLinkService {

    /**
     * 新增相关链接
     * @param modPackRelatedLinkParam 相关链接参数类
     * @return  结果
     */
    Result<Integer> insertRelatedLink(ModPackRelatedLinkParam modPackRelatedLinkParam);

    /**
     * 删除某个模组整合包相关链接
     * @param relatedLinkId 相关链接id
     * @return  结果
     */
    Result<Integer> deleteRelatedLink(Integer relatedLinkId);

    /**
     * 更改相关链接信息
     * @param modPackRelatedLinkParam 相关链接参数类
     * @return  结果
     */
    Result<Integer> updateRelatedLink(ModPackRelatedLinkParam modPackRelatedLinkParam);
}
