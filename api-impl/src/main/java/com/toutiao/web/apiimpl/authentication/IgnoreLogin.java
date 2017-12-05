package com.toutiao.web.apiimpl.authentication;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreLogin {
    boolean validate() default true;
}
