package com.powernode.bank.web.action;

import com.powernode.bank.bean.Account;
import com.powernode.bank.exception.NotSufficientFundsException;
import com.powernode.bank.exception.TransferException;

public class AccountService {

    private AccountDao accountDao = new AccountDao();

    public void transfer(String fromActno, String toActno, double money) throws NotSufficientFundsException, TransferException{
        int count = 0;
        // 查询转出的账户
        Account outAccount = accountDao.selectByActno(fromActno);
        // 判断转出账户的余额是否充足
        if(outAccount.getBalance() < money){
            throw new NotSufficientFundsException("余额不足");
        }
        // 查询转出账户
        Account inAccount = accountDao.selectByActno(toActno);

        // 在内存当中修改好数据然后传到DAO层
         outAccount.setBalance(outAccount.getBalance() - money);
         inAccount.setBalance(inAccount.getBalance() + money);

        count = accountDao.updateBalance(outAccount);
        count +=  accountDao.updateBalance(inAccount);

        if(count != 2){
            throw new TransferException("转账失败");
        }
    }
}
