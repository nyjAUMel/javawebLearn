package com.bjpowernode.javaweb.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

// ServletContextListener监听器主要监听的是ServletContext对象的状态
public class MyServletContextListener implements ServletContextListener {

    /**
     * 监听器的方法不需要手动调用，当放生某个特殊时间之后服务器自动调用
     * @param sce
     */

    @Override
    public void contextInitialized(ServletContextEvent sce) { //服务器启动的时候想执行的代码写在这
        // 该方法会在ServletContext对象创建之后自动调用
        System.out.println("ServletContext created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { // 服务器停止的时候型想执行的代码写在这
        //  该方法会在ServletContext对象销毁之前自动调用
        System.out.println("ServletContext destroyed");
    }
}
