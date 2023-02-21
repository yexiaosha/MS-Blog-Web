package com.ms.blog.service.impl;

import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.ModPackRelatedLinkMapper;
import com.ms.blog.entity.ModPackRelatedLink;
import com.ms.blog.entity.param.ModPackRelatedLinkParam;
import com.ms.blog.service.ModPackRelatedLinkService;
import com.ms.blog.util.ResultUtils;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 整合包相关链接业务接口实现
 * @author wyh
 * @date 2023/02/07 15:13
 */
@Service
@Slf4j
public class ModPackRelatedLinkServiceImpl implements ModPackRelatedLinkService {

    @Resource
    private ModPackRelatedLinkMapper modPackRelatedLinkMapper;

    @Override
    @ServiceLog("新增相关链接")
    public Result<Integer> insertRelatedLink(ModPackRelatedLinkParam modPackRelatedLinkParam) {
        ModPackRelatedLink modPackRelatedLink = ModPackRelatedLink.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .modPackId(modPackRelatedLinkParam.getModPackId())
                .name(modPackRelatedLinkParam.getName())
                .pic(modPackRelatedLinkParam.getPic())
                .url(modPackRelatedLinkParam.getUrl())
                .build();

        return ResultUtils.success(modPackRelatedLinkMapper.insertRelatedLink(modPackRelatedLink));
    }

    @Override
    @ServiceLog("删除某个模组整合包相关链接")
    public Result<Integer> deleteRelatedLink(Integer relatedLinkId) {
        return ResultUtils.success(modPackRelatedLinkMapper.deleteRelatedLink(relatedLinkId));
    }

    @Override
    @ServiceLog("更改相关链接信息")
    public Result<Integer> updateRelatedLink(ModPackRelatedLinkParam modPackRelatedLinkParam) {
        ModPackRelatedLink modPackRelatedLink = ModPackRelatedLink.builder()
                .createTime(new Date())
                .modPackId(modPackRelatedLinkParam.getModPackId())
                .name(modPackRelatedLinkParam.getName())
                .pic(modPackRelatedLinkParam.getPic())
                .url(modPackRelatedLinkParam.getUrl())
                .build();
        return ResultUtils.success(modPackRelatedLinkMapper.updateRelatedLink(modPackRelatedLink));
    }
}
