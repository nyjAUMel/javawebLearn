package oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-11 11:39
 */
public class DBUtil {

    // 静态变量，在类加载时执行。并且是有顺序的，自上而下的顺序
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    // 根据属性配置文件key获取对应的value
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接对象
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn 数据库连接对象
     * @param stmt 数据库操作对象
     * @param rs   结果集对象
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
