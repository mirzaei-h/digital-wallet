package com.digipay.wallet.services;

import com.digipay.wallet.base.BaseService;
import com.digipay.wallet.exceptions.BaseException;
import com.digipay.wallet.models.dto.MoneyTransfer;
import com.digipay.wallet.models.entity.TransactionEntity;

import java.util.List;

public interface TransactionService extends BaseService<TransactionEntity, Long> {
    void withdraw( TransactionEntity transaction) throws BaseException;
    void deposit(TransactionEntity transaction);
    void chargeWallet(TransactionEntity transaction);
    List<TransactionEntity> findByWallet (Long id);
    void moneyTransfer(MoneyTransfer moneyTransfer);

}
