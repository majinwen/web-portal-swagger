package com.toutiao.web.common.assertUtils;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by jyl on 17/9/8.
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NashEnumsValidator.class)
@Documented
public @interface NashEnumsCheck {
    String message() default "{枚举中未找到值}";

    Class value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
