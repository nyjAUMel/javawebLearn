package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-09 08:56
 * @Version: 1.0
 */
public class RequestTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        PrintWriter writer = resp.getWriter();
        writer.println(req);

        writer.println("<br/>");

        // 获取客户端的IP地址
        String remoteAddr = req.getRemoteAddr();
        writer.println("客户端的IP地址：" + remoteAddr);

        writer.println("<br/>");

        // 获取应用的根路径
        String contextPath = req.getContextPath();
        writer.println("根路径：" + contextPath);// /servlet08

        writer.println("<br/>");

        // 获取请求方式
        String method = req.getMethod();
        writer.println("请求方式：" + method);

        writer.println("<br/>");

        //  获取Servlet路径(不带项目名)
        String servletPath = req.getServletPath();
        writer.println("Servlet路径：" + servletPath); // /testRequest

        writer.println("<br/>");

        // 获取请求URI
        String requestURI = req.getRequestURI();
        writer.println("请求URI：" + requestURI);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* setCharacterEncoding()作用是设置请求体的字符编码。也就是设置post请求的乱码问题。
         * 因为post请求参数是存放在请求体中。
         * get请求参数是在请求行中，所以该方法无法解决get乱码问题。
         */
        req.setCharacterEncoding("utf8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("username:" + username);
        System.out.println("password:" + password);
    }
}
