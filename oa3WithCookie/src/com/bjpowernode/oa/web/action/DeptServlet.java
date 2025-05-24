package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.bean.Dept;
import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-16 11:39
 */
@WebServlet({"/dept/list", "/dept/detail", "/dept/delete", "/dept/save", "/dept/modify"})
public class DeptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 如果当前请求没有 Session → 不创建新 Session，直接返回 null。
        // 如果已有 Session → 正常返回现有 Session。
        HttpSession session = req.getSession(false);
        // 验证会话
        if (session != null && session.getAttribute("username") != null) {
            System.out.println(session.getAttribute("username"));
            // 获取请求路径
            String servletPath = req.getServletPath();
            switch (servletPath) {
                case "/dept/list":
                    doList(req, resp);
                    break;
                case "/dept/detail":
                    doDetail(req, resp);
                    break;
                case "/dept/delete":
                    doDelete_(req, resp);
                    break;
                case "/dept/save":
                    doSave(req, resp);
                    break;
                case "/dept/modify":
                    doModify(req, resp);
                default:
                    break;
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    /**
     * 修改部门信息
     *
     * @param req
     * @param resp
     */
    private void doModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deptNo = req.getParameter("deptno");
        String dName = req.getParameter("dname");
        String loc = req.getParameter("loc");

        Connection connection = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "update dept set dName = ?, loc = ? where deptno = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, dName);
            ps.setString(2, loc);
            ps.setString(3, deptNo);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, ps, null);
        }
        if (count == 1) {
            // 修改成功
            resp.sendRedirect(req.getContextPath() + "/dept/list");
        }
    }

    /**
     * 新增部门
     *
     * @param req
     * @param resp
     */
    private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptNo = req.getParameter("deptno");
        String dName = req.getParameter("dname");
        String loc = req.getParameter("loc");

        Connection connection = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptNo);
            ps.setString(2, dName);
            ps.setString(3, loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, ps, null);
        }
        if (count == 1)
            resp.sendRedirect(req.getContextPath() + "/dept/list");
    }

    /**
     * 根据部门编号删除部门
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doDelete_(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptNo = req.getParameter("deptNo");

        Connection connection = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from dept where deptno = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptNo);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, ps, null);
        }

        if (count == 1) {
            // 删除成功
            resp.sendRedirect(req.getContextPath() + "/dept/list");
        }
    }

    private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dept dept = null;

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select * from dept where deptno = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, req.getParameter("deptno"));
            rs = ps.executeQuery();
            if (rs.next()) {
                String deptNo = rs.getString("deptno");
                String dName = rs.getString("dname");
                String loc = rs.getString("loc");
                // 将以上零散的数据封装到Dept对象中收集起来
                dept = new Dept(deptNo, dName, loc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, ps, null);
        }
        // 将部门对象放到请求域中
        req.setAttribute("dept", dept);
        // req.getRequestDispatcher("/detail.jsp").forward(req, resp);

        /* String f = req.getParameter("f");
        if (f.equals("m")) {
            // 转发到修改页面
            req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        } else if (f.equals("d")) {
            // 转发到详情页面
            req.getRequestDispatcher("/detail.jsp").forward(req, resp);
        } */

        // detail或者edit
        String forward = "/" + req.getParameter("f") + ".jsp";
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    /**
     * 连接数据库，查询所有部门信息。将部门信息收集好，然后跳转到列表页面。
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        ArrayList<Dept> depts = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 连接数据库
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement("select * from dept");
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptNo = rs.getString("deptno");
                String dName = rs.getString("dname");
                String loc = rs.getString("loc");

                // 将以上零散的数据封装到Dept对象中收集起来
                Dept dept = new Dept();
                dept.setDeptNo(deptNo);
                dept.setDeptName(dName);
                dept.setLoc(loc);
                // 将部门对象放到集合中
                depts.add(dept);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        // 将一个集合放到请求域当中
        req.setAttribute("deptList", depts);

        // 转发
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
