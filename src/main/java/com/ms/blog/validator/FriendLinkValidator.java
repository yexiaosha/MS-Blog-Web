package com.ms.blog.validator;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ExcelCheckResult;
import com.ms.blog.common.base.BaseNonRegularExpressionValidator;
import com.ms.blog.entity.ExcelCheckErr;
import com.ms.blog.entity.FriendLink;
import com.ms.blog.entity.dto.FriendLinkDto;
import com.ms.blog.entity.param.FriendLinkParam;
import com.ms.blog.entity.vo.FriendLinkErrVo;
import com.ms.blog.service.LinksService;
import io.netty.util.internal.StringUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * 友情链接非正则表达式校验器
 * @author wyh
 * @date 2023/02/03 14:02
 */

@Slf4j
public class FriendLinkValidator extends BaseNonRegularExpressionValidator<FriendLink> {

    private static LinksService linksService;

    public static void setFriendLinkValidator(ApplicationContext applicationContext){
        linksService = applicationContext.getBean(LinksService.class);
    }

    @Override
    public ExcelCheckResult<FriendLink> nonValidateData(List<FriendLink> list) {
        List<FriendLink> successList = new ArrayList<>();
        List<ExcelCheckErr<FriendLink>> errList = new ArrayList<>();
        for (FriendLink f : list) {
            StringBuilder errMsg = new StringBuilder();
            FriendLinkDto friendLinkDto = new FriendLinkDto();
            friendLinkDto.setName(f.getName());
            if (linksService.getFriendLink(friendLinkDto)!= null){
                errMsg.append("网站名已存在");
            }
            friendLinkDto.setEmail(f.getEmail());
            if (linksService.getFriendLink(friendLinkDto) != null){
                errMsg.append("邮箱已存在");
            }
            friendLinkDto.setUrl(f.getUrl());
            if (linksService.getFriendLink(friendLinkDto) != null){
                errMsg.append("链接已存在");
            }
            if (StringUtil.isNullOrEmpty(errMsg.toString())){
                successList.add(f);
            }else {
                errList.add(new ExcelCheckErr<>(f, errMsg.toString()));
            }
        }
        return new ExcelCheckResult<>(successList, errList);
    }

    @Override
    public void saveData(List<FriendLink> list) {

        FriendLinkParam friendLinkParam = new FriendLinkParam();
        for (FriendLink f : list) {
            friendLinkParam.setInfo(f.getInfo());
            friendLinkParam.setEmail(f.getEmail());
            friendLinkParam.setName(f.getName());
            friendLinkParam.setUrl(f.getUrl());
            linksService.insertFriendLink(friendLinkParam);
        }

    }

    @Override
    public void outPutErrExcel(List<ExcelCheckErr<FriendLink>> errList, int sign) throws IOException {
        if (errList.isEmpty()){
            return;
        }

        OutputStream outputStream = new FileOutputStream("D:/Temp/Err" +sign + ".xlsx");
        List<FriendLinkErrVo> excelErrs = errList.stream().map(excelCheckErrs ->{
            FriendLinkErrVo friendLinkErrVo = JSON.parseObject(JSON.toJSONString(excelCheckErrs.getT()), FriendLinkErrVo.class);
            friendLinkErrVo.setErrMessage(excelCheckErrs.getErrMessage());
            return friendLinkErrVo;
        }).collect(Collectors.toList());
        EasyExcel.write(outputStream, FriendLinkErrVo.class).sheet("错误信息表").doWrite(excelErrs);
    }
}
