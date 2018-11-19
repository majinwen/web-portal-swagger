package com.toutiao.web.apiimpl.conf.interceptor;

import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.restmodel.NashResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * zhangjinglei 2017/8/31 上午9:54
 */
@ControllerAdvice
public class GlobalExceptionHandler extends AbstractErrorController {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Value("${exception.show}")
    private String showException = "";

    private ErrorAttributes errorAttributes;

    @Autowired
    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }


    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<Map> handleBaseException(HttpServletRequest request, HttpServletResponse response, BaseException ex) {
        String requestType = request.getHeader("x-requested-with");
        logger.error("异常:" + request.getRequestURI(), ex);
        Map map = new HashMap(2);
        map.put("code", ex.getCode());
        map.put("message", ex.getMsg());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<Map> handleIllegalArgumentException(HttpServletRequest request, HttpServletResponse response, IllegalArgumentException ex) {
        Map map = new HashMap(2);
        map.put("code", 99);
        map.put("message", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Map map = new HashMap(2);
        map.put("code", -1);
        map.put("message", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public void othersErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
//        String url = req.getRequestURI();
//        logger.error("request error at " + url, e);
//    }


    private String getExceptionDetail(Exception e) {
        StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");
        StackTraceElement[] messages = e.getStackTrace();
        int length = messages.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(messages[i].toString() + "\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}