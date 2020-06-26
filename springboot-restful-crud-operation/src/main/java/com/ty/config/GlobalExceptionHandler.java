package com.ty.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangyu
 * @date 2020/6/26
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public String exceptionHandler(UnauthorizedException e){
        System.out.println("未授权接口不能进行访问");
        return "未授权接口不能进行访问";
    }
}
