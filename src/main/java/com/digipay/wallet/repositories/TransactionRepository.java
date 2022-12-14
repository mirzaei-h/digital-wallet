package com.digipay.wallet.repositories;

import com.digipay.wallet.models.entity.TransactionEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByWallet (Optional<WalletEntity> walletEntity);
}
