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
 * @Create: 2025-05-11 14:17
 */
public class DeptListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取应用的根路径
        String contextPath = req.getContextPath();
        // 设置响应的内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from dept");
            resultSet = preparedStatement.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("        <html lang='en'>");
            out.println("        <head>");
            out.println("        <meta charset='UTF-8'>");
            out.println("        <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("        <title>部门列表页面</title>");
            out.println("        </head>");
            out.println("      <body>");
            out.println("        <h1 style='text-align: center;'>部门列表</h1>");
            out.println("        <hr />");
            out.println("        <table border='1' align='center' width='50%'>");
            out.println("        <thead>");
            out.println("        <tr>");
            out.println("        <th>序号</th>");
            out.println("        <th>部门编号</th>");
            out.println("        <th>部门名称</th>");
            out.println("        <th>操作</th>");
            out.println("        </tr>");
            out.println("        </thead>");
            out.println("        <tbody>");

            int i = 0; // 记录序号
            while (resultSet.next()) {
                String deptNo = resultSet.getString(1);
                String dName = resultSet.getString(3);
                out.println("<tr>");
                out.println("        <td>" + (++i) + "</td>");
                out.println("        <td>" + deptNo + "</td>");
                out.println("        <td>" + dName + "</td>");
                out.println("        <td>");
                out.println("        <a href='#'>删除</a>");
                out.println("        <a href='" + contextPath + "/dept/edit?deptno=" + deptNo + "'>修改</a>");
                out.println("        <a href='" + contextPath + "/dept/detail?deptno=" + deptNo + "'>详情</a>");
                out.println("        </td>");
                out.println("        </tr>");
            }
            out.println("</tbody>");
            out.println("      </table>");
            out.println("      <hr />");
            out.println("      <a href=" + contextPath + "/add.html" + ">新增部门</a>");
            out.println("            <script>");
            out.println("    const deptNos = document.querySelectorAll('tr td:nth-child(2)')");
            out.println("    const tds = document.querySelectorAll('tr td:nth-child(4)')");
            out.println("            tds.forEach((td,index) => {");
            out.println("                    td.children[0].addEventListener('click', function (e) {");
            out.println("                    e.preventDefault()");
            out.println("            if (confirm('确定要删除吗？bro')) {");
            out.println("                window.location.href = '" + contextPath + "/dept/delete?deptno='+" + "deptNos[index].innerText");
            out.println("            } else {");
            out.println("                // 用户取消删除，什么都不做");
            out.println("            }");
            out.println("      })");
            out.println("    })");
            out.println("");
            out.println("  </script>");
            out.println("      </body>");
            out.println("      </html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
