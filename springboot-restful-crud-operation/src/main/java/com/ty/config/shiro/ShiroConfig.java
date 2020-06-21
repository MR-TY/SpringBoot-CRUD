package com.ty.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tangyu
 * @date 2020/6/21
 */
@Configuration
public class ShiroConfig {

    // 1.shiroFilterFactoryBean:请求过滤都在这个类里面
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        // 设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /**
         * anon:无需认证就可以访问
         * authc:必须认证了才能访问
         * user：必须拥有 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */

        Map<String, String> filterMap = new LinkedHashMap<>();
        // 1.授权,设置登录用户必须有add这个权限才能进行访问此接口
        filterMap.put("/shiro/add","perms[user:add]");
        // 2.登录拦截
        // 任何没有登录的用户都能访问
        // filterMap.put("/add","anon");
        // 只有登录认证后的用户才能进行接口的访问
        filterMap.put("/shiro/*","authc");
        // 登录权限：如果没有权限，跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 授权权限：如果没有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauthor");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    //2.DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //3.创建realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
