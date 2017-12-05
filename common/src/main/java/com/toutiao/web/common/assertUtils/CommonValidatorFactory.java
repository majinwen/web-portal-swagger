package com.toutiao.web.common.assertUtils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by jyl on 17/9/8.
 */
public @Controller
@Scope("singleton")
class CommonValidatorFactory{
    private static final Validator validator;

    private CommonValidatorFactory(){}

    static{
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        System.out.println("validator加载成功！＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋＋");
    }

    public static Validator getInstance() {
        if(validator==null){
            throw new RuntimeException("validator没了！++++++++++++++++++++++");
        }
        return validator;
    }
}