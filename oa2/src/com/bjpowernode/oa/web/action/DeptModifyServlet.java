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
import java.util.Enumeration;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-13 17:02
 */
public class DeptModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决请求体乱码问题
        req.setCharacterEncoding("utf-8");
        // 获取表单数据
        String deptNo = req.getParameter("deptno");
        String dName = req.getParameter("dname");
        String loc = req.getParameter("loc");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "update dept set dname = ?, loc = ? where deptno = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,dName);
            ps.setString(2,loc);
            ps.setString(3,deptNo);
            int i = ps.executeUpdate();
            if (i > 0) {
                // 修改成功
                // req.getRequestDispatcher("/dept/list").forward(req,resp);

                resp.sendRedirect(req.getContextPath() + "/dept/list");
            } else {
                // 修改失败
                // req.getRequestDispatcher("/error.html").forward(req,resp);

                resp.sendRedirect(req.getContextPath() + "/error.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection,ps,null);
        }
    }
}
