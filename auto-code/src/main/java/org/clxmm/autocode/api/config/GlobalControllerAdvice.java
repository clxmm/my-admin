package org.clxmm.autocode.api.config;


import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.common.Result;
import org.clxmm.autocode.common.exception.ServiceException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    public static final String BAD_REQUEST_MSG = "客户端请求参数错误";
    // <1> 处理 form data方式调用接口校验失败抛出的异常

    // <1> 处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> "参数" + o.getField() + ":" + o.getDefaultMessage())
                .collect(Collectors.toList());
        return Result.error(collect, BAD_REQUEST_MSG);
    }

    /**
     * @param e
     * @return com.zhengxl.validationdemo.common.ResultInfo
     * @description RequestBody为 json 的参数校验异常捕获
     * @author 郑晓龙
     * @createTime 2020/7/28 18:07
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> "参数" + o.getField() + ":" + o.getDefaultMessage())
                .collect(Collectors.toList());
        return Result.error(collect, BAD_REQUEST_MSG);
    }


    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionHandler(ServiceException ex) {
        log.info("[serviceExceptionHandler]", ex);
        return Result.error(ex.getCode(), ex.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public Result exce(Exception ex) {
        log.info("[serviceExceptionHandler]", ex);
        return Result.error("系统异常，请联系管理员");
    }


}
