package com.ms.blog.service;

import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.entity.FriendLink;
import com.ms.blog.entity.dto.FriendLinkDto;
import com.ms.blog.entity.param.FriendLinkParam;
import com.ms.blog.entity.param.FriendLinkSearchParam;
import com.ms.blog.entity.vo.FriendLinkVo;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 友情链接业务接口
 * @author wyh
 * @date 2023/02/02 11:03
 */
public interface LinksService {

    /**
     * 获取友情链接
     * @param friendLinkSearchParam 友情链接参数
     * @return 通用结果
     */
    Result<PageData<FriendLinkVo>> getFriendLinks(FriendLinkSearchParam friendLinkSearchParam);

    /**
     * 新增友情链接
     * @param friendLinkParam   友情链接表单
     * @return 结果
     */
    Result<Integer> insertFriendLink(FriendLinkParam friendLinkParam);

    /**
     * 删除/下架友情链接
     * @param friendLinkParam 友情链接表单
     * @return 结果
     */
    Result<Integer> updateFriendLink(FriendLinkParam friendLinkParam);

    /**
     * 通过上传excel更新友情链接
     * @param response 响应对象
     * @param file 文件类
     */
    void uploadFriendLinkByExcel(HttpServletResponse response, MultipartFile file) throws IOException;

    /**
     * 下载模板
     * @param response 响应
     */
    void downloadTemplate(HttpServletResponse response);

    FriendLink getFriendLink(FriendLinkDto friendLinkDto);
}
