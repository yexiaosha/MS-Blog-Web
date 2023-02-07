package com.ms.blog.service.impl;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.ModPackMapper;
import com.ms.blog.dao.UserMapper;
import com.ms.blog.entity.ModPack;
import com.ms.blog.entity.ModPackRelatedLink;
import com.ms.blog.entity.param.ModPackParam;
import com.ms.blog.entity.vo.ModPackRelatedLinkVo;
import com.ms.blog.entity.vo.ModPackVo;
import com.ms.blog.service.ModPackService;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 整合包业务接口实现类
 *
 * @author wyh
 * @date 2023/02/05 15:19
 */
@Service
@Slf4j
public class ModPackServiceImpl implements ModPackService {

    @Resource
    private ModPackMapper modPackMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    @ServiceLog("获取整合包列表")
    public Result<List<ModPackVo>> getModPackList() {
        List<ModPack> modPackList = modPackMapper.getModPackList();
        List<ModPackVo> modPackVoList = new ArrayList<>();
        for (ModPack m : modPackList) {
            modPackVoList.add(copy(m));
        }
        return ResultUtils.success(modPackVoList);
    }

    @Override
    @ServiceLog("通过整合包id获取整合包详情")
    public Result<ModPackVo> getModPackById(Integer id) {
        ModPackVo modPackVo = copy(modPackMapper.getModPackById(id));
        return ResultUtils.success(modPackVo);

    }

    @Override
    @ServiceLog("更新整合包信息")
    public Result<Integer> updateModPackInfo(ModPackParam modPackParam) {
        modPackParam.setUpdateDate(new Date());
        modPackMapper.updateModPackInfo(modPackParam);
        return ResultUtils.success();
    }

    @Override
    @ServiceLog("更新作者")
    public Result<Integer> updateModPackCreator(Integer userId, Integer modPackId) {
        if (userMapper.getUserById(userId) == null || modPackMapper.getModPackById(modPackId) == null){
            return ResultUtils.fail("A0212", "用户或整合包不存在");
        }

        return ResultUtils.success(modPackMapper.updateModPackCreator(userId, modPackId));
    }

    private ModPackVo copy(ModPack modPack){
        ModPackVo modPackVo = new ModPackVo();
        BeanUtils.copyProperties(modPack, modPackVo);
        List<ModPackRelatedLink> linkList = modPackMapper.getRelatedLinkByModPackId(modPack.getId());
        List<ModPackRelatedLinkVo> modPackRelatedLinkVoList = new ArrayList<>();
        ModPackRelatedLinkVo modPackRelatedLinkVo = new ModPackRelatedLinkVo();
        for (ModPackRelatedLink l : linkList) {
            BeanUtils.copyProperties(l, modPackRelatedLinkVo);
            modPackRelatedLinkVoList.add(modPackRelatedLinkVo);
        }
        modPackVo.setRelatedLinkVoList(modPackRelatedLinkVoList);
        modPackVo.setCreatorList(modPackMapper.getCreatorByModPackId(modPack.getId()));
        return modPackVo;
    }
}
