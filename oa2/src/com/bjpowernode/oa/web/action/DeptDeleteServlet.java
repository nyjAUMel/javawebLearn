package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-12 08:51
 */
public class DeptDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptno = req.getParameter("deptno");
        System.out.println("deptno = " + deptno);

        // 开始删除
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            // 开启事物
            connection.setAutoCommit(false); // 关闭自动提交
            String sql = "delete from dept where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, deptno);
            int i = preparedStatement.executeUpdate();
            // 提交事物
            connection.commit();
            if (i > 0) {
                // 删除成功
                // req.getRequestDispatcher("/dept/list").forward(req, resp);

                resp.sendRedirect(req.getContextPath() + "/dept/list");
            } else {
                // 删除失败
                // req.getRequestDispatcher("/error.html").forward(req, resp);

                resp.sendRedirect(req.getContextPath() + "/error.html");
            }
        } catch (SQLException e) {
            // 回滚事物
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

}
