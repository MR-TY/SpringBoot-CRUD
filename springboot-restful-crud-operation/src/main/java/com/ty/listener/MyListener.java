package com.ty.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2018/5/8.
 */
public class MyListener implements ServletContextListener {
   public void contextInitialized(ServletContextEvent var1){
       System.out.println("启动web应用");
    }

   public void contextDestroyed(ServletContextEvent var1){

    }
}
