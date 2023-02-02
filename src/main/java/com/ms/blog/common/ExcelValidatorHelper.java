package com.ms.blog.common;

import com.alibaba.excel.annotation.ExcelProperty;
import java.lang.reflect.Field;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import lombok.NoArgsConstructor;

/**
 * excel正则校验方法
 * @author wyh
 * @date 2023/02/02 17:35
 */
@NoArgsConstructor
public class ExcelValidatorHelper {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> String validateEntity(T object) throws NoSuchFieldException{
        StringBuilder result = new StringBuilder();
        Set<ConstraintViolation<T>> set = validator.validate(object, Default.class);
        if (set != null && !set.isEmpty()){
            for (ConstraintViolation<T> cv : set) {
                Field declareField = object.getClass().getDeclaredField(cv.getPropertyPath().toString());
                ExcelProperty annotation = declareField.getAnnotation(ExcelProperty.class);
                result.append(annotation.value()[0]).append(cv.getMessage()).append(";");
            }
        }
        return result.toString();
    }
}
