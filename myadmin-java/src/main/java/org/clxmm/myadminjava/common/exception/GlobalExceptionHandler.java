package org.clxmm.myadminjava.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.myadminjava.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author clxmm
 * @Description
 * @create 2021-09-05 2:50 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public Result bindExceptionHandler(BindException e) {


        BindingResult result = e.getBindingResult();
        ObjectError objectError = result.getAllErrors().stream().findFirst().get();
        List<FieldError> list = result.getFieldErrors();
        List<String> l = list.stream().
                map(o -> o.getDefaultMessage()).collect(Collectors.toList());

        log.error("实体校验异常：----------------{}", l);
        return Result.fail(l.toString());
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        ObjectError objectError = result.getAllErrors().stream().findFirst().get();
        List<FieldError> list = result.getFieldErrors();
        List<String> l = list.stream().
                map(o -> o.getDefaultMessage()).collect(Collectors.toList());

        log.error("实体校验异常：----------------{}", l);
        return Result.fail(objectError.getDefaultMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public Result handler1(Exception e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return Result.fail(e.getMessage());
    }
}
