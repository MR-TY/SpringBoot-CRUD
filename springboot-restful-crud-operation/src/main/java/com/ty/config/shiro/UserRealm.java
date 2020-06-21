package com.ty.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @author tangyu
 * @date 2020/6/21
 * 自定义userRealm
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权info");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 登录的用户进行授权，这一般是从数据库里面的权限表进行获取权限
        info.addStringPermission("user:add");
        // 拿到当前登录用户
       /* Subject subject = SecurityUtils.getSubject();
          User user (User)subject.getPrincipal();//拿到了当前登录用户的实体
          这样通过获取到当前登录的用户就能获取它的权限，然后就能进行权限设置

        */
        return info;
    }

    /**
     * 用户   认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证info");
        String userName = "1";
        String password = "2";
        // 全局变量，只要调用登录接口，全局都能使用这个获取用户
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        if (!usernamePasswordToken.getUsername().equals(userName)){
            return null; //抛出异常catch (UnknownAccountException e){// 用户名为空
        }
        // 可以加密MD5加密，或者MD5盐值加密
        // 密码认证,shiro已经做了密码认证
        // 第一个参数按理说应该是从数据库获取的用户实体，保存为用户实体，这样就能通过subject获取用户实体
        return new SimpleAuthenticationInfo("",password,"");
    }
}
