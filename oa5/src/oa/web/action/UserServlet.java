package oa.web.action;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import oa.utils.DBUtil;

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
            // 删除Cookie
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName()) || "password".equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        /**
                         * 为什么光有setMaxAge(0)不行，还要cookie.setPath(req.getContextPath())和resp.addCookie(cookie)呢？
                         *  因为：
                         *      setPath()
                                        1. Cookie的标识不仅仅依赖于名称，还依赖于Path
                                        2. 浏览器会将不同Path的同名Cookie视为不同的Cookie
                                        3. 如果删除Cookie时的Path与创建时不同，浏览器会认为这是在操作另一个Cookie
                                        4. 所以必须确保删除时的Cookie配置（包括Path）与创建时完全一致
                                            这就像在文件系统中：
                                                /app/cookie1 和 /cookie1 是两个不同的文件
                                                要删除 /app/cookie1，你必须指定完整的正确路径，而不能只指定 /cookie1
                         *      addCookie()
                             *      1. Cookie的存储位置
                                        Cookie实际上是存储在客户端（浏览器）的
                                        服务器通过HTTP响应头中的Set-Cookie字段来操控浏览器对Cookie的处理
                                    2. setMaxAge(0)的作用
                                        cookie.setMaxAge(0)只是在Java对象层面设置了Cookie的过期时间
                                        这个操作仅仅改变了服务器端Cookie对象的属性
                                        但这个改变并没有被传达给客户端（浏览器）
                                    3. resp.addCookie(cookie)的必要性
                                        这个操作会将修改后的Cookie信息添加到HTTP响应头中
                                        它会在响应中添加一个Set-Cookie头，告诉浏览器这个Cookie已经过期
                                        浏览器收到这个响应后，才会实际删除存储的Cookie
                                总结：
                                     setMaxAge(0)     → 设置Cookie过期
                                     addCookie(cookie) → 通知浏览器执行删除
                         */
                        cookie.setPath(req.getContextPath());
                        resp.addCookie(cookie);
                    }
                }
            }
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
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
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
        String path = success ? "/dept/list" : "/index.jsp";

        // 重定向
        resp.sendRedirect(req.getContextPath() + path);
    }
}
