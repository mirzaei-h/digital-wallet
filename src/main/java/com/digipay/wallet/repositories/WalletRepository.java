package com.digipay.wallet.repositories;

import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long>  {
    List<WalletEntity> findByUser (UserEntity userEntity);
    WalletEntity findByWalletName(String name);

}
