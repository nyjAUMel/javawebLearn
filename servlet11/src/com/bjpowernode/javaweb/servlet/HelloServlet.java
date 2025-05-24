package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-13 22:43
 */
// 路径可以写多个
// 注解对象的使用格式：@注解名称(属性名=属性值, 属性名=属性值)
@WebServlet(name = "/hello", urlPatterns = {"/hello1", "/hello2"},
        // loadOnStartup = 1,
        initParams = {@WebInitParam(name = "nyj", value = "root"),
                @WebInitParam(name = "dj", value = "抽烟")})
public class HelloServlet extends HttpServlet {

    public HelloServlet() {
        System.out.println("无参数构造方法执行，HelloServlet对象创建了");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        PrintWriter writer = resp.getWriter();

        // 获取Servlet名
        String servletName = this.getServletName();
        writer.println("Servlet名：" + servletName);

        // 获取Servlet Path也就是web.xml里的url-pattern标签值
        String servletPath = req.getServletPath();
        writer.println("<br/>Servlet路径：" + servletPath);

        // 获取初始化参数，也就是注解里写的 initParams
        Enumeration<String> initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            String value = getInitParameter(name);
            writer.println("<br/>初始化参数：" + name + "=" + value);
        }
    }
}
