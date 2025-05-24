package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-04 09:54
 * @Version: 1.0
 */
public class ConfigTestServlet2 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        ServletConfig servletConfig = getServletConfig();

        writer.println(servletConfig);
    }
}
