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

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-12 15:22
 */
public class DeptEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        // 获取编号
        String deptNo = req.getParameter("deptno");

        PrintWriter writer = resp.getWriter();
        // 获取应用根路径
        String contextPath = req.getContextPath();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 连接数据库
        try {
            connection = DBUtil.getConnection();
            String sql = "select dname, loc as location from dept where deptno = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptNo);
            rs = ps.executeQuery();

            // 渲染
            writer.println("   <!DOCTYPE html>");
            writer.println("      <html lang='en'>");
            writer.println("      <head>");
            writer.println("      <meta charset='UTF-8'>");
            writer.println("      <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            writer.println("      <title>修改部门</title>");
            writer.println("      </head>");
            writer.println("      <body>");
            writer.println("      <h1>新增部门</h1>");
            writer.println("      <hr />");
            writer.println("      <form action='" + contextPath + "/dept/modify' method='post'>");
            // 这个结果集只有一条数据
            if (rs.next()) {
                String dName = rs.getString("dname");
                String loc = rs.getString("location"); // 这里不是表的列名，而是查出来的字段名
                writer.println("部门编号<input type='text' name='deptno' value='" + deptNo + "' readonly><br />");
                writer.println("部门名称<input type='text' name='dname' value='" + dName + "'><br />");
                writer.println("部门位置<input type='text' name='loc' value=" + loc + "><br />");
                writer.println("<input type='submit' value='修改'><br />");
            }
            writer.println("  </form>");
            writer.println("        </body>");
            writer.println("        </html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, ps, rs);
        }
    }
}