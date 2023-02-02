package com.ms.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.aspect.annotation.ServiceLog;
import com.ms.blog.dao.LinksMapper;
import com.ms.blog.entity.FriendLink;
import com.ms.blog.entity.param.FriendLinkParam;
import com.ms.blog.entity.param.FriendLinkSearchParam;
import com.ms.blog.entity.vo.FriendLinkVo;
import com.ms.blog.service.LinksService;
import com.ms.blog.util.ExcelUtil;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 友情链接业务实现
 * @author wyh
 * @date 2023/02/02 11:04
 */
@Service
public class LinksServiceImpl implements LinksService {

    @Resource
    private LinksMapper linksMapper;

    @Override
    @ServiceLog("获取友情链接")
    public Result<PageData<FriendLinkVo>> getFriendLinks(FriendLinkSearchParam friendLinkSearchParam) {
        Page<FriendLink> friendLinkPage = new Page<>(friendLinkSearchParam.getCurrentPage(), friendLinkSearchParam.getPageSize());
        IPage<FriendLink> friendLinkIPage = linksMapper.getFriendLinks(friendLinkPage, friendLinkSearchParam);
        List<FriendLink> friendLinkList = friendLinkIPage.getRecords();
        List<FriendLinkVo> friendLinkVoList = new ArrayList<>();
        FriendLinkVo friendLinkVo = new FriendLinkVo();
        for (FriendLink f : friendLinkList) {
            BeanUtils.copyProperties(f, friendLinkVo);
            friendLinkVoList.add(friendLinkVo);
        }
        PageData<FriendLinkVo> pageData = new PageData<>(friendLinkVoList, friendLinkIPage.getTotal(), friendLinkIPage.getPages(), friendLinkIPage.getCurrent());
        return ResultUtils.success(pageData);
    }

    @Override
    @ServiceLog("新增友情链接")
    public Result<Integer> insertFriendLink(FriendLinkParam friendLinkParam) {
        FriendLink friendLink = FriendLink.builder()
                .creatTime(new Date())
                .email(friendLinkParam.getEmail())
                .name(friendLinkParam.getName())
                .reason(friendLinkParam.getReason())
                .info(friendLinkParam.getInfo())
                .updateTime(new Date())
                .status(1)
                .url(friendLinkParam.getUrl())
                .avatar(friendLinkParam.getAvatar())
                .build();
        return ResultUtils.success(linksMapper.insertFriendLink(friendLink));
    }

    @Override
    @ServiceLog("变更友情链接信息")
    public Result<Integer> updateFriendLink(FriendLinkParam friendLinkParam) {
        FriendLink friendLink = linksMapper.getFriendLinkByName(friendLinkParam.getName());

        if (friendLink == null) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        return ResultUtils.success(linksMapper.updateFriendLinkInfo(friendLinkParam));
    }

    @Override
    @ServiceLog("解析表并批量新增友情链接")
    public void uploadFriendLinkByExcel(HttpServletResponse response, MultipartFile file) {

    }

    @Override
    @ServiceLog("模板下载")
    public void downloadTemplate(HttpServletResponse response) {
        String filename = "友情链接表格模板";
        int columnWidth = 20;
        ExcelUtil.downloadTemplate(filename, null, columnWidth, response, FriendLink.class);
    }

}
