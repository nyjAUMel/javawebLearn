package com.powernode.bank.service;

import com.powernode.bank.exception.AppException;
import com.powernode.bank.exception.NotSufficientFundsException;
import com.powernode.bank.exception.TransferException;

public interface AccountService {
    public void transfer(String fromActno, String toActno, double money) throws NotSufficientFundsException, TransferException, AppException;

}
