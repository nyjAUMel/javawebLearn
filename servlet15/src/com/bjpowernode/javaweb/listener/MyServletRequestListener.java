package com.bjpowernode.javaweb.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println( "request initialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request destroyed");
    }
    
}
