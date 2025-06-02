package com.powernode.bank.dao;

import com.powernode.bank.pojo.Account;

public interface AccountDao {

    Account selectByActno(String actno);
    public int updateBalance(Account account);
}
