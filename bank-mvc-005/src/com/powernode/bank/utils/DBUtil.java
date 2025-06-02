package com.powernode.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {

    /*
     * 不让创建对象，因为工具类中的方法都是静态方法，不需要实例化
     * */
    private DBUtil() {
    }


    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");
    private static final String driver = bundle.getString("driver");
    private static final String url = bundle.getString("url");
    private static final String user = bundle.getString("username");
    private static final String password = bundle.getString("password");

    // 类加载时注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = local.get();
         if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
                local.set(conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    /**
     * 关闭数据库连接，预编译语句，结果集
     *
     * @param conn
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
                /**
                 * 移除的原因
                 * Tomcat服务器支持线程池技术，也就是一个人用完了t1线程，t1线程还有可能被别人使用
                 */
                local.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
