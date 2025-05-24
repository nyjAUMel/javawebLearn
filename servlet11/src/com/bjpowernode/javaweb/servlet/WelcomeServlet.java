package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-13 23:05
 */
//@WebServlet(urlPatterns = {"/welcome1", "/welcome2"})

// 注意：当注解的属性是一个数组，并且数组中只有一个值时，可以省略大括号
//@WebServlet(urlPatterns = "/welcome")

// value属性和urlPatterns属性是等价的，都是用来指定Servlet的映射路径的。
//@WebServlet(value = {"/welcome1", "/welcome2"})
// @WebServlet(value = "/welcome1")
// 并且属性名如果是value，那么可以省略value属性名，直接写属性值。
@WebServlet("/welcome1")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        PrintWriter writer = resp.getWriter();

        writer.println("欢迎来到Servlet");
    }
}
