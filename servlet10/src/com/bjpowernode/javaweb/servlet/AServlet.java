package com.bjpowernode.javaweb.servlet;

import com.bjpowernode.javaweb.bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-13 17:26
 */
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User dj = new User(1, "丁真");

        // 存储到请求域当中
        req.setAttribute("userObj", dj);

        // 转发
        //req.getRequestDispatcher("/b").forward(req, resp);

        /*
        * 重定向
        *
        * 重定向时的路径需要以项目名开始，或者说需要添加项目名。
        * response将这个路径“/servlet10/b”发送给了浏览器
        * 浏览器收到这个路径，就会自发的向服务器发送一次全新的请求：localhost:8080/servlet10/b
        * 所以浏览器一共发送了两次请求，
        *   第一次请求是：localhost:8080/servlet10/a
        *   第二次请求是：localhost:8080/servlet10/b
        * 最终浏览器地址栏显示的地址是最后一次重定向的地址，所以说重定向会导致浏览器的地址改变
        *  */
        resp.sendRedirect(req.getContextPath() + "/b");
    }
}
