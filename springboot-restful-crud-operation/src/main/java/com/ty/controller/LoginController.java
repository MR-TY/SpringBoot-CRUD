package com.ty.controller;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/6.
 */
@Controller
public class LoginController {
    //在rest风格中专门有接收post请求的注解
    // @RequestMapping(value = "user/login",method = RequestMethod.POST)
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/user/login")
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String loginSuccess(@RequestParam("Username") String userName,
                               @RequestParam("Password") String passWord,
                               Map<String, Object> map, HttpSession session) {
        logger.info("进入到登录的页面");
        if (!StringUtils.isEmpty("userName") & "123".equals(passWord)) {
            session.setAttribute("userName", userName);
            logger.info("进入到登录的成功");
            //登录成功之后，防止再按F5之后，页面重复提交，就利用重定向解决
            return "redirect:/main.html";
        } else {
            map.put("msg", "用户名或者密码错误");
            return "index";
        }
    }
}
