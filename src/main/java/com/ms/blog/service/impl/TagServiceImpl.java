package com.ms.blog.service.impl;

import com.ms.blog.common.Result;
import com.ms.blog.entity.vo.TagVo;
import com.ms.blog.service.TagService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 标签类接口实现
 * @author wyh
 * @date 2023/01/31 17:08
 */
@Service
public class TagServiceImpl implements TagService {

    @Override
    public Result<List<TagVo>> getTagIdByName(String tagName) {
        return null;
    }
}
