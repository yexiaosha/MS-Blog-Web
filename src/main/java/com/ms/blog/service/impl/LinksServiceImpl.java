package com.ms.blog.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.ErrorCode;
import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.common.annotation.ServiceLog;
import com.ms.blog.dao.LinksMapper;
import com.ms.blog.entity.FriendLink;
import com.ms.blog.entity.dto.FriendLinkDto;
import com.ms.blog.entity.param.FriendLinkParam;
import com.ms.blog.entity.param.FriendLinkSearchParam;
import com.ms.blog.entity.vo.FriendLinkVo;
import com.ms.blog.listener.UploadDataListener;
import com.ms.blog.service.LinksService;
import com.ms.blog.util.ExcelUtil;
import com.ms.blog.util.ResultUtils;
import com.ms.blog.validator.FriendLinkValidator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 友情链接业务实现
 * @author wyh
 * @date 2023/02/02 11:04
 */
@Service
@Slf4j
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

        friendLinkParam.setId(friendLink.getId());
        return ResultUtils.success(linksMapper.updateFriendLinkInfo(friendLinkParam));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadFriendLinkByExcel(HttpServletResponse response, MultipartFile file) throws IOException{
        response.setContentType("application/vnd.ms-excel");

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        InputStream inputStream = file.getInputStream();
        UploadDataListener<FriendLink> listener = new UploadDataListener<>(FriendLinkValidator.class);
        EasyExcel.read(inputStream, FriendLink.class, listener).sheet().doRead();

        String filePath = "D:\\Temp" + File.separator;
        String zipName = "ErrorTable" + ".zip";
        if (listener.getSign() != 1){
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipName, "UTF-8"));

            new ZipFile(filePath + zipName).addFiles(getErrFiles(filePath, listener.getSign()));

            OutputStream outputStream = response.getOutputStream();
            File zipFile = new File(filePath + zipName);
            InputStream input = new FileInputStream(zipFile);
            try {
                byte[] buf = new byte[1024];
                int read;
                while ((read = input.read(buf)) > 0) {
                    outputStream.write(buf, 0, read);
                }
            } finally {
                input.close();
                outputStream.close();
            }
        }
    }

    @Override
    @ServiceLog("模板下载")
    public void downloadTemplate(HttpServletResponse response) {
        String filename = "友情链接表格模板";
        int columnWidth = 20;
        ExcelUtil.downloadTemplate(filename, null, columnWidth, response, FriendLink.class);
    }

    @Override
    public FriendLink getFriendLink(FriendLinkDto friendLinkDto) {
        return linksMapper.getFriendLink(friendLinkDto);
    }

    public List<File> getErrFiles(String filePath, int sign) {
        List<File> files = new LinkedList<>();
        log.info(String.valueOf(sign));
        for (int i = 0; i < sign-1; i++) {
            files.add(new File(filePath + "Err" + (i + 1) + ".xlsx"));
        }

        return files;
    }

}
