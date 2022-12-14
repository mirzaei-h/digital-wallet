package com.digipay.wallet.services.impl;

import com.digipay.wallet.exceptions.BaseException;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import com.digipay.wallet.models.enums.WalletStatus;
import com.digipay.wallet.repositories.WalletRepository;
import com.digipay.wallet.services.UserService;
import com.digipay.wallet.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final WalletRepository walletRepository;
    private final UserService userService;

    private UserEntity getUsernameBySecurityContextHolder() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userService.findByUsername(username);
        return user;
    }
    @Override
    @Transactional
    public void save(WalletEntity walletEntity) {
        LOGGER.info("saving new wallet:{} to db", walletEntity);
        if (walletEntity.getWalletId() == null) {
            walletEntity.setBalance(0.0);
            walletEntity.setStatus(WalletStatus.ACTIVE);
        }
        UserEntity user = getUsernameBySecurityContextHolder();
        walletEntity.setUser(user);
        walletRepository.save(walletEntity);
    }

    @Override
    @Transactional
    public void update(WalletEntity walletEntity) {
        LOGGER.info("updating wallet:{} to db", walletEntity);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
        Optional<WalletEntity> wallet = findById(walletEntity.getWalletId());
        walletEntity.setWalletName(wallet.get().getWalletName());
        walletEntity.setStatus(wallet.get().getStatus());
        if(wallet.get().getWalletName() != null && wallet.get().getUser().getUsername().equals(username))
            walletRepository.save(walletEntity);
        else throw new BaseException("just owner wallet can update the wallet");
    }
    @Override
    @Transactional
    public List<WalletEntity> findByExample(WalletEntity walletEntity) {
        LOGGER.info("finding wallets:{} to db", walletEntity);
        //todo extend this method and  add QuerydslPredicateExecutor<WalletEntity> to walletRepository
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("walletName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("walletId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return walletRepository.findAll(Example.of(walletEntity, customExampleMatcher));
    }

    @Override
    @Transactional
    public List<WalletEntity> findByUser() {
        LOGGER.info("finding wallets by user to db");
        UserEntity user = getUsernameBySecurityContextHolder();
        return walletRepository.findByUser(user)
                .stream()
                .sorted(Comparator.comparing(WalletEntity::getCreateDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WalletEntity> findById(Long id) {
        LOGGER.info("finding wallet by id to db");
        return walletRepository.findById(id);
    }
    @Override
    public WalletEntity findByWalletName(String name) {
        LOGGER.info("finding wallet by walletName to db");
        return walletRepository.findByWalletName(name);
    }

}
