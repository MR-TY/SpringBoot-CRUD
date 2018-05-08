package com.ty.config;
import com.ty.component.LoginHandlerInterceptor;
import com.ty.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.rmi.registry.Registry;

/**
 * Created by Administrator on 2018/5/5.
 * @Configuration:标注这是一个配置类
 * @EnableWebMvc:全面接管了mvc
 */
@Configuration
//@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter{
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送ty请求也来到success的页面
      registry.addViewController("/ty").setViewName("success");
    }
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter= new  WebMvcConfigurerAdapter(){
            public void addViewControllers(ViewControllerRegistry registry) {
                //视图映射
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //springBoot已经注册好了静态资源，所以就不用处理静态资源
                //增加拦截的页面，并且排除不拦截的页面
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
//                        excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }

    //把自定义的local在容器中进行申明，这样就能获取值
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
