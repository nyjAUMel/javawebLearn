package com.powernode.bank.web.action;

import com.powernode.bank.bean.Account;
import com.powernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {

    /**
     * 根据账号查询账户信息
     * @param actno
     * @return
     */
    public Account  selectByActno(String actno) {

        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from account where actno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, actno);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setActno(rs.getString("actno"));
                account.setBalance(rs.getDouble("balance"));
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, stmt, rs);
        }
        return null;
    }

    public int updateBalance(Account account) {

        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = null;
        int count = 0;
        try {

            String sql = "update account set balance = ? where actno = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getActno());
            count = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, stmt, null);}
        return count;
    }
}
