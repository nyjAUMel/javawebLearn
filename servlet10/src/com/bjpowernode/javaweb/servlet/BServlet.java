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
 * @Author nyjAUMel
 * @Create: 2025-05-13 17:26
 */
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 从请求域当中取出存储的数据
        Object userObj = req.getAttribute("userObj");

        // 输出到浏览器
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("userObj: " + userObj);
    }
}
