package com.ms.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.entity.Article;
import com.ms.blog.entity.FriendLink;
import com.ms.blog.entity.dto.FriendLinkDto;
import com.ms.blog.entity.param.FriendLinkParam;
import com.ms.blog.entity.param.FriendLinkSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 友情链接接口
 * @author wyh
 * @date 2023/02/02 11:02
 */
@Mapper
public interface LinksMapper extends BaseMapper<Article> {

    /**
     * 获取友情链接列表
     * @param friendLinkPage 分页参数
     * @param friendLinkSearchParam 友情链接参数
     * @return 友情链接列表
     */
    IPage<FriendLink> getFriendLinks(Page<FriendLink> friendLinkPage, FriendLinkSearchParam friendLinkSearchParam);

    /**
     * 新增友情链接
     * @param friendLink 友情链接类
     * @return  结果
     */
    int insertFriendLink(FriendLink friendLink);

    /**
     * 通过友情链接id获取友情链接
     * @param name 友情链接名称
     * @return 友情链接对象
     */
    FriendLink getFriendLinkByName(@Param("name") String name);

    /**
     * 更改友情链接信息
     * @param friendLinkParam 更改参数
     * @return 结果
     */
    int updateFriendLinkInfo(FriendLinkParam friendLinkParam);

    /**
     * 验证
     * @param friendLinkDto 数据类
     * @return 结果
     */
    FriendLink getFriendLink(FriendLinkDto friendLinkDto);
}
