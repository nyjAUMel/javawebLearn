package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-19 11:04
 */
@WebServlet(urlPatterns = {"/user/login", "/user/logout"})
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/user/login":
                doLogin(req, resp);
                break;
            case "/user/logout":
                doLogout(req, resp);
                break;
            default:
                break;
        }
    }

    /**
     * 退出登录，清除session
     *
     * @param req
     * @param resp
     */
    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取session对象，销毁session
        HttpSession session = req.getSession(false);
        if (session != null) {
            // 手动删除
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean success = false;
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select * from t_user where username = ? and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            // 这个结果只有一条数据
            success = rs.next() ? true : false; // 登录成功为true，反之为false

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, ps, rs);
        }
        // 判断是否登录成功拼接不同的页面
        if (success) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
        }
        String path = success ? "/dept/list" : "/error.jsp";

        // 重定向
        resp.sendRedirect(req.getContextPath() + path);
    }
}
