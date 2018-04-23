package com.toutiao.web.common.assertUtils;

import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author WuShoulei on 2018/2/2
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumber.Validator.class)
@Documented
public @interface PhoneNumber {

    String message() default "invalid phone number";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<PhoneNumber, String> {

        @Override
        public void initialize(PhoneNumber phone){

        }

        @Override
        public boolean isValid(String phone, ConstraintValidatorContext arg1){
            if(StringTool.isNotEmpty(phone)){
                return StringUtil.isCellphoneNo(phone);
            }else{
                return true;
            }
        }
    }
}
