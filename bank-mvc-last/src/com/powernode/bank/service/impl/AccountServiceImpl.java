package com.powernode.bank.service.impl;

import com.powernode.bank.dao.AccountDao;
import com.powernode.bank.dao.impl.AccountDaoImpl;
import com.powernode.bank.pojo.Account;
import com.powernode.bank.exception.AppException;
import com.powernode.bank.exception.NotSufficientFundsException;
import com.powernode.bank.exception.TransferException;
import com.powernode.bank.service.AccountService;
import com.powernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    public void transfer(String fromActno, String toActno, double money) throws NotSufficientFundsException, TransferException, AppException {
        Connection conn = null;
        // service层控制事务
        // 开启事务(需要使用Connection对象)
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            // 查询转出的账户
            Account outAccount = accountDao.selectByActno(fromActno);
            // 判断转出账户的余额是否充足
            if (outAccount.getBalance() < money) {
                throw new NotSufficientFundsException("余额不足");
            }
            // 查询转出账户
            Account inAccount = accountDao.selectByActno(toActno);

            // 在内存当中修改好数据然后传到DAO层
            outAccount.setBalance(outAccount.getBalance() - money);
            inAccount.setBalance(inAccount.getBalance() + money);

            int count = accountDao.updateBalance(outAccount);

            // 模拟异常
            /*String str = null;
            str.toString();*/

            count += accountDao.updateBalance(inAccount);

            if (count != 2) {
                throw new TransferException("转账失败");
            }
            conn.commit();

        } catch (SQLException e) {
            // try()这种写法catch里访问不到conn对象，所以无法在catch里回滚事务。
            throw new AppException("转账失败");
        } finally {
            DBUtil.close(conn, null, null);
        }
    }
}
