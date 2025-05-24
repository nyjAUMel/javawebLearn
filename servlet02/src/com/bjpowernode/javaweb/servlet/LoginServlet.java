package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-04-30 12:23
 * @Version: 1.0
 */
public class LoginServlet extends GenericServlet {


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("LoginServlet.service");
    }
}
