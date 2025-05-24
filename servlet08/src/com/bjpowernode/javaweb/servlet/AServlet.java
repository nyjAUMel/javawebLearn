package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-09 15:49
 * @Version: 1.0
 */
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", "丁真");

        /**
         * 由于HttpServletRequest是请求域对象，每次请求创建一个。不同的Servlet对应不同的请求域。
         * 所以AServlet存储的数据，在BServlet中无法获取。
         *
         * 为了解决这个问题，我们可以使用转发
         */
        Object name = req.getAttribute("name");

        // 转发
        // 1. 获取请求转发器对象(/b就是在web.xml注册的url-pattern)
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/b");
        // 2. 调用请求转发器对象RequestDispatcher的forward方法
        requestDispatcher.forward(req, resp);
    }

}
