package com.ms.blog.service.impl;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.ModPackMapper;
import com.ms.blog.entity.ModPack;
import com.ms.blog.entity.vo.ModPackVo;
import com.ms.blog.service.ModPackService;
import com.ms.blog.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    @ServiceLog("获取整合包列表")
    public Result<List<ModPackVo>> getModPackList() {
        List<ModPack> modPackList = modPackMapper.getModPackList();
        List<ModPackVo> modPackVoList = new ArrayList<>();
        ModPackVo modPackVo = new ModPackVo();
        for (ModPack m : modPackList) {
            BeanUtils.copyProperties(m, modPackVo);
            modPackVoList.add(modPackVo);
        }
        return ResultUtils.success(modPackVoList);
    }

    @Override
    @ServiceLog("通过整合包id获取整合包详情")
    public Result<ModPackVo> getModPackById(Integer id) {
        ModPackVo modPackVo = ModPackVo.builder().build();
        BeanUtils.copyProperties(modPackMapper.getModPackById(id), modPackVo);
        return ResultUtils.success(modPackVo);

    }
}
