package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-04-30 15:27
 * @Version: 1.0
 */
public class UserServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("UserServlet执行");
    }
}
