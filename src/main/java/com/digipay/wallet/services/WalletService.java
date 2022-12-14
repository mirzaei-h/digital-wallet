package com.digipay.wallet.services;

import com.digipay.wallet.base.BaseService;
import com.digipay.wallet.models.entity.WalletEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface WalletService extends BaseService<WalletEntity, Long>  {

    List<WalletEntity> findByUser ();
    List<WalletEntity> findByExample(WalletEntity walletEntity);
    Optional<WalletEntity> findById(Long id);
    WalletEntity findByWalletName(String name);
    void update(WalletEntity walletEntity);

}
