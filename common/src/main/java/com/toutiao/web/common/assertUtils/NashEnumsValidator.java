package com.toutiao.web.common.assertUtils;

import com.toutiao.web.common.constant.base.IntBaseType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jyl on 17/9/8.
 */
public class NashEnumsValidator implements ConstraintValidator<NashEnumsCheck, Object> {
    Class<Enum> value;
    @Override
    public void initialize(NashEnumsCheck constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Enum[] enumConstants = value.getEnumConstants();
        if(obj==null){
            return true;
        }
        if(enumConstants.length > 0) {
            for (Enum e : enumConstants) {
                try {
                    IntBaseType b = (IntBaseType) e;
                    if (obj instanceof Integer) {
                        if (b.getValue() == Integer.valueOf(String.valueOf(obj))) {
                            return true;
                        }
                    } else if (obj instanceof Boolean) {
                        if (b.getValue() == (Boolean.valueOf(String.valueOf(obj)) ? 1 : 0)) {
                            return true;
                        }
                    } else if (obj instanceof String) {

                    }
                } catch (Exception ex) {
                    throw new RuntimeException("枚举没有实现IntBaseType接口");
                }
            }
        }
        return false;
    }
}