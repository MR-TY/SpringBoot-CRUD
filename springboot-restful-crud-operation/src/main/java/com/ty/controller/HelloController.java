package com.ty.controller;

import com.ty.Exception.CustomerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

/**
 * Created by Administrator on 2018/5/8.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String getException(@RequestParam("user") String user) {
        System.out.println("进入到测试页面");
        if ("aaa".equals(user)) {
            throw new CustomerException();
        }
        return "success";
    }
}
