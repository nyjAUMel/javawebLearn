package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-04 15:26
 * @Version: 1.0
 */
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();

        ServletContext application = this.getServletContext();

        writer.println("servletContext对象: " + application);
        writer.println("<br/>");

        // 获取上下文初始化参数
        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            // 通过<param-name>pageSize</param-name>标签的键(pageSize)获取<param-value>10</param-value>标签的值(10)
            String initParameter = application.getInitParameter(s);
            writer.println(s + ": " + initParameter);
            writer.println("<br/>");
        }

        // 获取contextPath(获取应用上下文的根)
        String contextPath = application.getContextPath();
        writer.println("contextPath: " + contextPath);
        writer.println("<br/>");

        /*
            ServletContext 的 getRealPath(String path) 方法在 Servlet API 中用于将 Web 应用程序中的虚拟路径映射到服务器文件系统上的真实路径。
            以斜杠 / 开头表示相对于 Web 应用程序的根目录。(斜杠可以省略)
         */
        String realPath1 = application.getRealPath("/index.html");
        String realPath2 = application.getRealPath("common/common.html");
        writer.println("index.html的绝对路径: " + realPath1);
        writer.println("<br/>");
        writer.println("common.html的绝对路径: " + realPath2);
        writer.println("<br/>");


        User user = new User("丁真", "123");
        // 向ServletContext应用域当中存储数据
        application.setAttribute("userKey", user);
        // 取出来
        Object userKey = application.getAttribute("userKey");
        // 输出到浏览器
        writer.println("userKey: " + userKey);
        writer.println("<br/>");
    }
}
