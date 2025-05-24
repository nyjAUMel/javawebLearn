package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-04 09:54
 * @Version: 1.0
 */
public class ConfigTestServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        ServletConfig servletConfig = this.getServletConfig();

        writer.println(servletConfig);

        writer.println("<br/>");

        /**
         * ServletConfig 对象在 Servlet 的生命周期中扮演着一个很重要的角色。它的主要作用是：在 Servlet 初始化时，向 Servlet 提供配置信息。
         */

        //Servlet 的名称 (getServletName()): 这是在 web.xml 文件中为 Servlet 配置的 <servlet-name>。每个 Servlet 实例都有其对应的名称。
        writer.println("Servlet 的名称：" + servletConfig.getServletName());

        writer.println("<br/>");

        //Servlet 的初始化参数 (getInitParameter(String name) 和 getInitParameterNames()): 这是在 web.xml 文件中为 Servlet 配置的 <init-param>。通过这些参数，您可以在部署时为 Servlet 实例传递一些配置信息，而无需硬编码在 Servlet 类中。例如，数据库的连接 URL、用户名、密码，或者一些应用的初始化设置等。
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) { // 是否有更多元素
            String s = initParameterNames.nextElement(); // 取元素名
            String initParameter = servletConfig.getInitParameter(s);// 根据初始化参数的名字获取值
            writer.println(s + ": " + initParameter);
            writer.println("<br/>");
        }


        ServletContext servletContext = servletConfig.getServletContext();
        writer.println("ServletContext 的名称：" + servletContext);
        writer.println("<br/>");
    }
}
