package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-12 14:22
 */
public class DeptSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取部门信息
        String deptNo = req.getParameter("deptno");
        String dName = req.getParameter("dname");
        String loc = req.getParameter("loc");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "insert into dept values(?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, deptNo);
            preparedStatement.setString(2, dName);
            preparedStatement.setString(3, loc);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                // 保存成功
                // 转发是一次请求
                //req.getRequestDispatcher("/dept/list").forward(req, resp);

                // 这里最好改成重定向(浏览器会发送一次新的请求，get)
                resp.sendRedirect(req.getContextPath() + "/dept/list");

            } else {
                System.out.println("添加失败");

                // req.getRequestDispatcher("/error.html").forward(req, resp);

                // 这里也建议重定向
                resp.sendRedirect(req.getContextPath() + "/error.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
