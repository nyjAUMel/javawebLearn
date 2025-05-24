package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * Description:
 * GenericServlet就是一个适配器，允许子类继承。只实现service()方法
 *
 * @Author 夕川AU
 * @Create: 2025-04-30 12:22
 * @Version: 1.0
 */
public abstract class GenericServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
