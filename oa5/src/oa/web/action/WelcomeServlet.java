package oa.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import oa.bean.User;
import oa.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = null;
        String password = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                System.out.println(name);
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }
        if (username != null && password != null) {
            // 连接数据库校验从Cookie拿到的账号密码对不对
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            // 记录是否登陆成功，用来解决cookies里默认有JSESSIONID的cookie
            // 在账号和密码都不对的情况下跳转到登录页
            boolean success = false;
            try {
                conn = DBUtil.getConnection();
                String sql = "select * from t_user where username = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    // 登录成功
                    success = true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.close(conn, ps, rs);
            }
            // 登陆成功
            if (success) {
                HttpSession session = req.getSession();
                User user = new User(username, password);
                session.setAttribute("user", user);
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                // 重定向到部门列表
                resp.sendRedirect(req.getContextPath() + "/dept/list");
            } else {
                // 登录失败，跳转到登陆页面
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        } else {
            // 没有Cookie，重新登陆
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
