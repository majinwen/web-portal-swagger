package com.toutiao.web.common.assertUtils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerParaValidator.IntegerParaCheker.class)
@Documented
public @interface IntegerParaValidator {

    String value();

    String message() default "this is a houseTypeValidator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class IntegerParaCheker implements ConstraintValidator<IntegerParaValidator,Integer> {

        String values;

        @Override
        public void initialize(IntegerParaValidator integerParaValidator) {
            this.values = integerParaValidator.value();
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            //切割有效值
            String[] para = values.split(",");
            boolean isFlag = false;

            if (null==value){
                isFlag = true;
            }

            //等于指定值
            for(String s:para){
                if (s.equals(String.valueOf(value))){
                    isFlag = true;
                }
            }
            return isFlag;
        }
    }
}
