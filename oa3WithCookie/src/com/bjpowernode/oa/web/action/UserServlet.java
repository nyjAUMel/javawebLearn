package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

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
            // 确定选择十天免登录，创建Cookie实现十天免登录
            if ("1".equals(req.getParameter("remember"))) {
                // 创建Cookie 存储登录名和密码
                Cookie cookie1 = new Cookie("loginName", username);
                Cookie cookie2 = new Cookie("loginPassword", password);
                cookie1.setMaxAge(60 * 60 * 24 * 7); // 7天
                cookie2.setMaxAge(60 * 60 * 24 * 7); // 7天
                // 设置Cookie的Path，只要访问这个应用，就要携带Cookie
                cookie1.setPath(req.getContextPath());
                cookie2.setPath(req.getContextPath());
                // 响应Cookie给浏览器br
                resp.addCookie(cookie1);
                resp.addCookie(cookie2);
            }
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
        }
        String path = success ? "/dept/list" : "/error.jsp";

        // 重定向
        resp.sendRedirect(req.getContextPath() + path);
    }
}
