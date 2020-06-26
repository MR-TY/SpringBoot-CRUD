package com.ty.component;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Administrator on 2018/5/5.
 * 可以在连接上携带区域信息
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        //如果获取到值了就得到以下local的值，如果没有获取就得到系统默认的值
        Locale locale = Locale.getDefault();
        //判断请求的参数是否为空
        if (!StringUtils.isEmpty(l)) {
            String[] strings = l.split("_");
            //分别拿到语言代码和国家代码
            locale = new Locale(strings[0], strings[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {

    }
}
