package com.bjpowernode.javaweb.servlet;

import com.bjpowernode.javaweb.bean.User1;
import com.bjpowernode.javaweb.bean.User2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/session/binding")
public class HttpSessionBindingListenerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取session对象
        HttpSession session = req.getSession();

        User1 user1 = new User1("111", "张三", "123456");
        User2 user2 = new User2("111", "李四", "123456");

        // 将user1对象绑定到session域中
        session.setAttribute("user1", user1);
        // 将user2对象绑定到session域中
        session.setAttribute("user2", user2);
    }
}
