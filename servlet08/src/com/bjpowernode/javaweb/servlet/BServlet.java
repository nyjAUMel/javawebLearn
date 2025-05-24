package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-09 15:49
 * @Version: 1.0
 */
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object name = req.getAttribute("name");
        System.out.println("name = " + name);

        /*
         * 转发不仅可以转发到Servlet, 还可以转发到页面。只要是web容器中的资源，都可以转发到。
         * 使用以斜杠 / 开头的相对路径，即相对于 Web 应用程序根目录的路径(不加项目名)。
         *  */
        req.getRequestDispatcher("/test.html").forward(req, resp);
    }
}
