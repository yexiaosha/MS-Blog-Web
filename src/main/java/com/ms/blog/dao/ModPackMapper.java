package com.ms.blog.dao;

import com.ms.blog.entity.ModPack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 整合包接口
 *
 * @author wyh
 * @date 2023/02/05 15:23
 */

@Mapper
public interface ModPackMapper {
    /**
     * 获取整合包列表
     * @return 整合包列表
     */
    List<ModPack> getModPackList();

    /**
     * 通过整合包id获取整合包详情
     * @param id 整合包id
     * @return 整合包详情类
     */
    ModPack getModPackById(Integer id);
}
