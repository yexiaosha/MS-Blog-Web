package com.ms.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.blog.common.PageData;
import com.ms.blog.common.Result;
import com.ms.blog.dao.LogMapper;
import com.ms.blog.entity.ExceptionLog;
import com.ms.blog.entity.UserLog;
import com.ms.blog.entity.param.UserLogParam;
import com.ms.blog.entity.vo.UserLogVo;
import com.ms.blog.service.LogService;
import com.ms.blog.util.ResultUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 日志业务实现类
 * @author wyh
 * @date 2023/01/10 14:42
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void insertUserLog(UserLog userLog) {
        logMapper.insertUserLog(userLog);
    }

    @Override
    public void insertExceptionLog(ExceptionLog exceptionLog) {
        logMapper.insertExceptionLog(exceptionLog);
    }

    @Override
    public Result<PageData<UserLogVo>> getUserLogs(UserLogParam userLogParam) {
        Page<UserLog> userLogPage = new Page<>(userLogParam.getCurrentPage(), userLogParam.getPageSize());
        IPage<UserLog> userLogIPage = logMapper.getUserLogs(userLogParam, userLogPage);
        List<UserLogVo> userLogVoList = new ArrayList<>();
        for (UserLog l : userLogIPage.getRecords()) {
            UserLogVo userLogVo = new UserLogVo();
            BeanUtils.copyProperties(l, userLogVo);
            userLogVoList.add(userLogVo);
        }
        PageData<UserLogVo> userLogVoPageData = new PageData<>(userLogVoList, userLogIPage.getTotal(), userLogIPage.getPages(), userLogIPage.getCurrent());
        return ResultUtils.success(userLogVoPageData);
    }

    @Override
    public Result<Integer> deleteUserLogById(Integer id) {
        return ResultUtils.success(logMapper.deleteUserLogById(id));
    }
}
