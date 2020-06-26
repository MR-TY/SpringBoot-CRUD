package com.ty.shiroController;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangyu
 * @date 2020/6/21
 */
@Controller
public class ShiroController {

    @GetMapping("/success")
    public String index(Model model) {
        model.addAttribute("hi","nihao");
        return "/success";
    }

    @GetMapping("/shiro/add")
    @RequiresPermissions("user:add1")
    public String add(Model model) {
        model.addAttribute("hi","nihao");
        return "/add";
    }

    @GetMapping("/shiro/update")
    @RequiresPermissions("user:update")
    public String update(Model model) {
        model.addAttribute("hi","nihao");
        return "/update";
    }

    @GetMapping("/toLogin")
    public String toLogin(Model model) {
        model.addAttribute("hi","nihao");
        return "/login";
    }

    @RequestMapping("/login")
    public String toLogin(String userName, String password,Model model) {
        Subject subject = SecurityUtils.getSubject();
        // 封装用户名，与密码加密为token
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
            return "success";
        } catch (UnknownAccountException e){// 用户名为空
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e){// 密码为空
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/noauthor")
    @ResponseBody
    public String noauthor(String userName, String password,Model model) {
        return "未授权不能进行访问";
    }
}
