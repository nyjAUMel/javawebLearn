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
 * @Create: 2025-05-11 15:22
 */
public class DeptDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptno = req.getParameter("deptno");

        // 设置响应的内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select deptno, loc, dname from dept where deptno = ?");
            preparedStatement.setString(1, deptno);
            resultSet = preparedStatement.executeQuery();
            out.println("<!DOCTYPE html>");
            out.println("        <html lang='en'>");
            out.println("        <head>");
            out.println("        <meta charset='UTF-8'>");
            out.println("        <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("        <title>部门详情</title>");
            out.println("        </head>");
            out.println("      <body>");
            out.println("      <h1>部门详情</h1>");
            out.println("      <hr />");

            // 结果集只有一条数据
            if (resultSet.next()) {
                String deptNo = resultSet.getString(1);
                String loc = resultSet.getString(2);
                String dName = resultSet.getString(3);

                out.println("部门编号：" + deptNo + "<br/>");
                out.println("部门名称：" + dName + "<br/>");
                out.println("部门位置：" + loc + "<br/>");
            }
            out.println("<input type='button' value='返回' onclick='history.back()'>");
            out.println("      </body>");
            out.println("      </html>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

    }
}
