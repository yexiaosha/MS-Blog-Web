package com.ms.blog.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ExcelValidatorHelper;
import com.ms.blog.entity.ExcelCheckErr;
import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * excel解析监听器
 * @author wyh
 * @date 2023/02/02 17:13
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class UploadDataListener<T> extends AnalysisEventListener<T> {

    private static int BATCH_COUNT = 5;
    private static int MAX_ERROR = 100;

    private List<T> successList = new ArrayList<>();
    private List<ExcelCheckErr<T>> errList = new ArrayList<>();

    List<T> list = new ArrayList<>();
    private Class<Service> service;

    private Class<T> clazz;
    private int sign = 1;

    public UploadDataListener(Class<Service> service){
        this.service = service;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        super.onException(exception, context);
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(t));
        String errMsg;
        try {
            errMsg = ExcelValidatorHelper.validateEntity(t);
        }catch (NoSuchFieldException e){
            errMsg = "数据解析异常";
            log.error(errMsg, e);
        }

        if (!StringUtil.isNullOrEmpty(errMsg)){
            ExcelCheckErr<T> excelCheckErr = new ExcelCheckErr<>(t,errMsg);
            errList.add(excelCheckErr);
        }else {
            list.add(t);
        }

        if (list.size() >= BATCH_COUNT){

        }
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        super.extra(extra, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return super.hasNext(context);
    }
}
