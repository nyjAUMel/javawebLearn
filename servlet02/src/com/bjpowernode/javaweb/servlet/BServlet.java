package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-04-29 15:50
 * @Version: 1.0
 */
public class BServlet implements Servlet {

    public BServlet() {
        System.out.println("BServlet构造器执行");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws IOException {

    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
