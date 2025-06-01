package com.bjpowernode.javaweb.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

    // 以下三个方法都是WEB容器调用的

    // 向session域当中存储数据时触发
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("attributeAdded");
    }

    // 从session域当中删除数据时触发
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("attributeRemoved");
    }

    //  session域当中的数据被替换时触发
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("attributeReplaced");
    }
}
