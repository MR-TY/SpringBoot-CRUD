package com.ty.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/8.
 */
@Component
public class MyFilter implements Filter {
    public void init(FilterConfig var1) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
