package com.ms.blog.listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ExcelCheckResult;
import com.ms.blog.common.ExcelValidatorHelper;
import com.ms.blog.common.base.BaseNonRegularExpressionValidator;
import com.ms.blog.entity.ExcelCheckErr;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

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
    private Class<? extends BaseNonRegularExpressionValidator<T>> validator;

    private Class<T> clazz;
    private int sign = 1;

    public UploadDataListener(Class<? extends BaseNonRegularExpressionValidator<T>> validator) {
        this.validator = validator;
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
        } catch (NoSuchFieldException e) {
            errMsg = "数据解析异常";
            log.error(errMsg, e);
        }

        if (!StringUtil.isNullOrEmpty(errMsg)) {
            ExcelCheckErr<T> excelCheckErr = new ExcelCheckErr<>(t, errMsg);
            errList.add(excelCheckErr);
        } else {
            list.add(t);
        }

        try {
            Method validatorMethod = validator.getDeclaredMethod("nonValidateData", List.class);
            validatorMethod.setAccessible(true);
            if (list.size() >= BATCH_COUNT) {
                @SuppressWarnings("unchecked")
                ExcelCheckResult<T> result = (ExcelCheckResult<T>) validatorMethod.invoke(validator.newInstance(),
                        list);
                successList.addAll(result.getSuccesses());
                errList.addAll(result.getErrs());
                saveData(successList);
                successList.clear();
                list.clear();
            }

            if (errList.size() >= MAX_ERROR){
                outPutErrorExcel(errList);
                errList.clear();
                sign++;
            }

        } catch (NoSuchMethodException | IOException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        super.extra(extra, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        try {
            Method method = validator.getDeclaredMethod("nonValidateData", List.class);
            method.setAccessible(true);

            @SuppressWarnings("unchecked")
            ExcelCheckResult<T> result = (ExcelCheckResult<T>) method.invoke(validator.newInstance(),
                        list);
            successList.addAll(result.getSuccesses());
            errList.addAll(result.getErrs());
            saveData(successList);
            outPutErrorExcel(errList);
            list.clear();
            errList.clear();
            successList.clear();
            log.info("所有数据解析完毕");
        } catch (NoSuchMethodException| IllegalAccessException | InvocationTargetException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return super.hasNext(context);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (clazz != null) {
            try {
                Map<Integer, String> indexNameMap = getIndexNameMap(clazz);
                Set<Integer> keySet = indexNameMap.keySet();
                for (Integer key : keySet) {
                    if (StringUtil.isNullOrEmpty(headMap.get(key))) {
                        throw new ExcelAnalysisException("解析Excel出错");
                    }
                    if (!headMap.get(key).equals(indexNameMap.get(key))) {
                        throw new ExcelAnalysisException("解析Excel出错");
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<Integer, String> getIndexNameMap(Class<?> clazz) throws NoSuchFieldException {
        Map<Integer, String> result = new HashMap<>(20);
        Field field;
        Field[] fields = clazz.getDeclaredFields();

        for (Field item : fields) {
            field = clazz.getDeclaredField(item.getName());
            field.setAccessible(true);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty != null) {
                int index = excelProperty.index();
                String[] values = excelProperty.value();
                StringBuilder value = new StringBuilder();
                for (String v : values) {
                    value.append(v);
                }
                result.put(index, value.toString());
            }
        }
        return result;
    }

    private void saveData(List<T> list){
        if (!list.isEmpty()){
            try {
                Method method = validator.getDeclaredMethod("saveData", List.class);
                method.invoke(validator.newInstance(), list);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        log.info("操作成功");
    }

    private void outPutErrorExcel(List<ExcelCheckErr<T>> errList) throws IOException {
        try {
            Method method = validator.getDeclaredMethod("outPutErrExcel", List.class ,int.class);
            method.invoke(validator.newInstance(), errList, sign);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}