package com.powernode.bank.web.action;

import com.powernode.bank.bean.Account;
import com.powernode.bank.exception.AppException;
import com.powernode.bank.exception.NotSufficientFundsException;
import com.powernode.bank.exception.TransferException;
import com.powernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService {

    private AccountDao accountDao = new AccountDao();

    public void transfer(String fromActno, String toActno, double money) throws NotSufficientFundsException, TransferException, AppException {

        // service层控制事务
        // 开启事务(需要使用Connection对象)
        try (Connection conn = DBUtil.getConnection();) {
            conn.setAutoCommit(false);
            System.out.println(conn);
            // 查询转出的账户
            Account outAccount = accountDao.selectByActno(fromActno, conn);
            // 判断转出账户的余额是否充足
            if (outAccount.getBalance() < money) {
                throw new NotSufficientFundsException("余额不足");
            }
            // 查询转出账户
            Account inAccount = accountDao.selectByActno(toActno, conn);

            // 在内存当中修改好数据然后传到DAO层
            outAccount.setBalance(outAccount.getBalance() - money);
            inAccount.setBalance(inAccount.getBalance() + money);

            int count = accountDao.updateBalance(outAccount, conn);

            // 模拟异常
            /*String str = null;
            str.toString();*/

            count += accountDao.updateBalance(inAccount, conn);

            if (count != 2) {
                throw new TransferException("转账失败");
            }
            conn.commit();
            // try() 里面会自动关闭，所以这里关闭是多余的
            conn.close();
        } catch (SQLException e) {
            // try()这种写法catch里访问不到conn对象，所以无法在catch里回滚事务。
            throw new AppException("转账失败");
        }
    }
}
