package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-04-29 15:50
 * @Version: 1.0
 */
public class StudentServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws IOException {
        // 设置响应的内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String username = "root";
            String password = "123456789";
            conn = DriverManager.getConnection(url, username, password);
            // 获取预编译数据库操作对象
            String sql = "select * from t_student";
            ps = conn.prepareStatement(sql);
            // 执行SQL
            rs = ps.executeQuery();
            // 处理结果
            while (rs.next()) {
                String no = rs.getString("no");
                String name = rs.getString("name");
                writer.print(no + "," + name + "<br/>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
