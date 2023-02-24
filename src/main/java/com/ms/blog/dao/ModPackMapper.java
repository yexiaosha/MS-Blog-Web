package com.ms.blog.dao;

import com.ms.blog.entity.ModPack;
import com.ms.blog.entity.ModPackRelatedLink;
import com.ms.blog.entity.param.ModPackParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

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

    /**
     * 通过整合包id获取整合包相关链接
     * @param id 整合包id
     * @return 相关链接表
     */
    List<ModPackRelatedLink> getRelatedLinkByModPackId(Integer id);

    /**
     * 通过模组整合包id获取整合包作者
     * @param modPackId 整合包id
     * @return 作者列表
     */
    List<Integer> getUserIdByModPackId(Integer modPackId);



    /**
     * 更新整合包基本信息
     * @param modPackParam 整合包表单
     * @return  结果
     */
    int updateModPackInfo(ModPackParam modPackParam);

    /**
     * 通过整合包名字获取整合包
     * @param name 名称
     * @return 整合包
     */
    ModPack getModPackByName(String name);

    /**
     * 更改整合包传作者
     * @param userId    用户id
     * @param modPackId 模组id
     * @return 结果
     */
    int updateModPackCreator(Integer userId, Integer modPackId);
}
