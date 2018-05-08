package com.ty.component;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/5/6.
 * 添加拦截器,进行登录检查
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
//   //目标方法执行之前进行检查用户是否登录
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取session中的值，看是否进行登录过
        Object user = request.getSession().getAttribute("userName");
        System.out.println("拦截器拦截的登录名："+user);
        if (user==null){
            //2.未进行登录,返回登录页面
            request.setAttribute("msg","没有权限请登录");
            //3.转发页面到登录页面
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            //4.已经登录放行
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }
}
