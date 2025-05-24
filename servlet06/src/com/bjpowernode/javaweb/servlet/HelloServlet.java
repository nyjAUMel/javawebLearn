package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-07 14:24
 * @Version: 1.0
 */
public class HelloServlet extends HttpServlet {

    /* @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
    } */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义doGet方法");
    }

    /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义doPost方法");
    } */
}
