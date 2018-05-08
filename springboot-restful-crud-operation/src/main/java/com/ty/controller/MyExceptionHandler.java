package com.ty.controller;

import com.ty.Exception.CustomerException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/8.
 */
@ControllerAdvice
public class MyExceptionHandler {
    //添加具有自适应的效果
        @ExceptionHandler(CustomerException.class)
        public String getException(Exception e, HttpServletRequest request) {
            Map<String, Object> map = new HashMap<>();
            //放到map中这个自定义错误信息能在前端获取进行打印
            map.put("code", "user.notexit");
            //e.getMessage()这个信息是来自于：CustomerException中抛出错误
            map.put("message", e.getMessage());
            //设置抛出的错误类型
            request.setAttribute("javax.servlet.error.status_code",500);
            //把错误的信息放进域对象中
            request.setAttribute("ext",map);
            //转发到error
            return "forward:/error";
        }
}
