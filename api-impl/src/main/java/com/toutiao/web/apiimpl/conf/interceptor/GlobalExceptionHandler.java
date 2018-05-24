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
import java.util.Set;

/**
 * zhangjinglei 2017/8/31 上午9:54
 */
@ControllerAdvice
@Component
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalExceptionHandler extends AbstractErrorController {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Value("${exception.show}")
    private String showException="";

    private ErrorAttributes  errorAttributes;
    @Autowired
    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(produces = "text/html")
    public String handleHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        return "redirect:/404";
        return "404";
    }
    @RequestMapping
    @ResponseBody
    public Object handleJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return NashResult.Fail("server-error");
    }


    private boolean isRestful(HttpServletRequest request){
        if(request.getRequestURI().toLowerCase().lastIndexOf("/rest/")>-1){
            return true;
        }
        return false;
    }


    // 异常处理方法：
    // 根据特定的异常返回指定的 HTTP 状态码
//    @ResponseStatus(value= HttpStatus.OK)  // 400
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Object handleValidationException(HttpServletRequest request,HttpServletResponse response,ConstraintViolationException ex) {
        logger.error("异常:"+request.getRequestURI(),ex);
        if(isRestful(request)){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            return NashResult.Fail(HttpStatus.BAD_REQUEST.toString(),"找不到接口地址",getExceptionDetail(ex));
        }
        String requestType = request.getHeader("x-requested-with");

        Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        NashResult nashResult = new NashResult();
        for (ConstraintViolation<?> violation : errors) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        logger.error("Bad_request-error", strBuilder.toString());
        if(requestType==null){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            return new ModelAndView("404");
        }else{
            nashResult = NashResult.Fail(HttpStatus.BAD_REQUEST.toString(),"找不到接口地址");
            response.setStatus(Integer.valueOf(HttpStatus.BAD_REQUEST.toString()));
            return nashResult;
        }
    }


    @ResponseStatus(value=HttpStatus.OK)
    @ExceptionHandler(value=MethodArgumentNotValidException.class) //400
    @ResponseBody
    public Object MethodArgumentNotValidHandler(HttpServletRequest request, HttpServletResponse response,
                                                    MethodArgumentNotValidException exception) throws Exception
    {
        String requestType = request.getHeader("x-requested-with");
        logger.error("异常:"+request.getRequestURI(),exception);
        if(isRestful(request)){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            return NashResult.Fail(HttpStatus.BAD_REQUEST.toString(),"请求参数错误",getExceptionDetail(exception));
        }
        HashMap<String,String> errortip=new HashMap<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errortip.put(error.getField(),error.getDefaultMessage());
        }
        logger.error("Argument-error","请求参数错误",errortip);
        NashResult nashResult = new NashResult();
        if(requestType==null){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            return new ModelAndView("404");
//            return "404";
        }else{
            nashResult = NashResult.Fail(HttpStatus.BAD_REQUEST.toString(),"请求参数错误");
            response.setStatus(Integer.valueOf(HttpStatus.BAD_REQUEST.toString()));
            return nashResult;
        }
    }

//    @ResponseStatus(value=HttpStatus.OK)  // 404
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object NoHandlerFoundException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String requestType = request.getHeader("x-requested-with");
        logger.error("NoHandlerFound-error","找不到接口地址："+request.getRequestURI().toString());
        if(isRestful(request)){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            return NashResult.Fail(HttpStatus.NOT_FOUND.toString(),"找不到接口地址",getExceptionDetail(ex));
        }
        NashResult nashResult = new NashResult();
        if(requestType==null){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            return  new ModelAndView("404");
        }else{
            nashResult = NashResult.Fail(HttpStatus.NOT_FOUND.toString(),"找不到接口地址");
            response.setStatus(Integer.valueOf(HttpStatus.NOT_FOUND.toString()));
            return nashResult;
        }
    }


    /**
     * @author  wangzw
     * @create  2018/3/2 16:36
     * @desc 无法抛出的参数异常
     **/
    @ResponseStatus(value=HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public NashResult BindException(HttpServletRequest request,HttpServletResponse response,BindException be){
        logger.error("异常:"+request.getRequestURI(),be);
        if(isRestful(request)){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            return NashResult.Fail("Argument-error","Argument-error|请求参数错误",getExceptionDetail(be));
        }
        return NashResult.Fail(	"Argument-error","Argument-error|请求参数错误");
    }
    // 通用异常的处理，返回500

//    @ResponseStatus(value=HttpStatus.OK)  // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException( HttpServletRequest request,HttpServletResponse response,Exception ex) {
        String requestType = request.getHeader("x-requested-with");
        logger.error("异常:"+request.getRequestURI(),ex);
        if(isRestful(request)){
            response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
            BaseException e=(BaseException)ex;
            return NashResult.Fail(e.getCode().toString(),e.getMsg(),getExceptionDetail(ex));
        }
        NashResult nashResult = new NashResult();
        if(ex instanceof BaseException){
            BaseException baseex=(BaseException)ex;
            logger.error("异常:"+baseex.getCode(),baseex.getMessage());
            if(requestType== null){
                response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
                return new ModelAndView("404");
            }else{
                nashResult = NashResult.Fail(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"服务器处理异常");
                response.setStatus(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
                return nashResult;
            }
        }
        if(this.showException!=null && this.showException.toLowerCase().equals("true")) {
            logger.error("server-error", this.getExceptionDetail(ex));
            if(requestType== null){
                response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
                return new ModelAndView("404");
            }else{
                response.setStatus(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
                nashResult = NashResult.Fail(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"服务器处理异常");
                return nashResult;
            }
        }
        else {
            logger.error("server-error", "服务器处理异常");
            if(requestType== null){
                response.setStatus(Integer.valueOf(HttpStatus.OK.toString()));
                return new ModelAndView("404");
            }else{
                response.setStatus(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
                nashResult = NashResult.Fail(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"服务器处理异常");
                return nashResult;
            }
        }
    }

    private String getExceptionDetail(Exception e) {
        StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");
        StackTraceElement[] messages = e.getStackTrace();
        int length = messages.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(messages[i].toString()+"\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}