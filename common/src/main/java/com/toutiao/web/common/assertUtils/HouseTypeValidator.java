package com.toutiao.web.common.assertUtils;


import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HouseTypeValidator.HouseTypeCheker.class)
@Documented
public @interface HouseTypeValidator {

    String value();

    String message() default "this is a houseTypeValidator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class HouseTypeCheker implements ConstraintValidator<HouseTypeValidator,String>{

        String values;

        @Override
        public void initialize(HouseTypeValidator constraintAnnotation) {
            this.values = constraintAnnotation.value();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            //分割有效值
            String[] type = values.split(",");
            boolean isFlag = false;

            //不能等于空字符串
            if ("".equals(value)){
                return false;
            }

            //当在大首页时为空值
            if (StringUtils.isEmpty(value)){
                return true;
            }

            //遍历比对的有效值
            for (int i = 0;i<type.length;i++){
                //存在一致跳出循环,并赋值isFlag=true
                if (type[i].equals(value)){
                    isFlag = true;
                    break;
                }
            }
            return isFlag;
        }
    }
}

