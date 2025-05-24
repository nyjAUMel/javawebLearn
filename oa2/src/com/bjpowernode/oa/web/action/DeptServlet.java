package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.annotation.WebServlet;
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
 * @Create: 2025-05-14 09:28
 */
// 模板类
@WebServlet(urlPatterns = {"/dept/save", "/dept/detail", "/dept/list", "/dept/delete", "/dept/modify", "/dept/edit"})
public class DeptServlet extends HttpServlet {

    // 重写service方法(因为doGet和doPost其实也是service在调)
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {
            case "/dept/save":
                save(request, response);
                break;
            case "/dept/detail":
                detail(request, response);
                break;
            case "/dept/list":
                list(request, response);
                break;
            case "/dept/delete":
                delete(request, response);
                break;
            case "/dept/modify":
                modify(request, response);
                break;
            case "/dept/edit":
                edit(request, response);
                break;
            default:
                break;
        }
    }

    private void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            ps.setString(1, dName);
            ps.setString(2, loc);
            ps.setString(3, deptNo);
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
            DBUtil.close(connection, ps, null);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
