package com.ms.blog.service;

import com.ms.blog.common.Result;
import com.ms.blog.entity.param.ModPackParam;
import com.ms.blog.entity.vo.ModPackVo;

import java.util.List;

/**
 * 整合包业务接口类
 *
 * @author wyh
 * @date 2023/02/05 15:18
 */
public interface ModPackService {
    /**
     * 获取整合包列表
     * @return  通用结果
     */
    Result<List<ModPackVo>> getModPackList();

    /**
     * 根据整合包id获取整合包信息
     * @param id 整合包id
     * @return 整合包视图类
     */
    Result<ModPackVo> getModPackById(Integer id);

    /**
     * 更新整合包信息
     * @param modPackParam 整合包表单
     * @return  通用结果
     */
    Result<Integer> updateModPackInfo(ModPackParam modPackParam);

    Result<Integer> updateModPackCreator(Integer userId, Integer modPackId);
}
