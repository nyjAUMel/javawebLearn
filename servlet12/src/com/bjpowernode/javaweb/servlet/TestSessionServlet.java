package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test/session")
public class TestSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // request和session都是服务器的java对象，都在JVM当中
        // request对象代表一次请求。(一次请求一个request对象。同理，两次请求就代表了两个不同的请求)
        // session对象代表一次会话。(一次会话对应一个session对象)

        // 获取session对象
        HttpSession session = req.getSession();

        resp.setContentType("text/html;charset=utf8");
        PrintWriter writer = resp.getWriter();
        writer.println(session);
    }
}
