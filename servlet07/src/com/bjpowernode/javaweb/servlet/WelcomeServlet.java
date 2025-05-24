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
 * @Create: 2025-05-08 17:49
 * @Version: 1.0
 */
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>欢迎来到Servlet</h1>");
    }

    /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>欢迎来到Servlet</h1>");
    } */
}
