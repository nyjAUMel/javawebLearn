package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie/generate")
public class GenerateCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建Cookie对象
        Cookie cookie1 = new Cookie("name1", "zhangsan");
        Cookie cookie2 = new Cookie("name2", "DingZhen");
        /**
         * 设置Cookie的有效期，单位为秒，-1表示关闭浏览器就失效，0表示立即失效，默认为-1
         * 如果没设置有效时间(也就是默认的-1)就会存在浏览器运行内存当中，关闭浏览器则失效
         * 但设置了有效时间就会存储到硬盘当中
         */
//        cookie1.setMaxAge(60 * 60 * 24 * 7); //7天
        cookie1.setMaxAge(-1);
        cookie2.setMaxAge(-1);
        /**
         * 当不手动设置路径时（即不调用 setPath），Cookie 的默认路径由 当前请求的 URL 路径 决定，规则如下：
         * 默认路径 = 请求 URL 的最深层父路径
         * 例如：
         * 请求 URL 为 /servlet13/cookie1/generate
         * 默认路径会被设置为 /servlet13/cookie1/（最后一个 / 后的部分被忽略）
         *
         */
        //手动设置Cookie的路径
        cookie1.setPath("/servlet13"); // 表示Cookie有效路径为/servlet13及其子路径

        //将Cookie对象添加到响应中
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);

    }
}
