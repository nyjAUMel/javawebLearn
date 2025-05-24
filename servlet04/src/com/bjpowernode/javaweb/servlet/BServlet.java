package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-04 15:26
 * @Version: 1.0
 */
public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();

        ServletContext servletContext = this.getServletContext();

        writer.println("servletContext对象: " + servletContext);
        writer.println("<br/>");

        // 输出日志(这里是输出到idea内置的tomcat)
        servletContext.log("丁真丁真丁真丁真丁真丁真丁真丁真丁真丁真丁真丁真");

        // 取出ServletContext应用域当中存储的数据
        Object userKey = servletContext.getAttribute("userKey");
        // 输出到浏览器
        writer.println("userKey: " + userKey);
        writer.println("<br/>");
    }
}
