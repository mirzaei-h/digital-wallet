package com.digipay.wallet.services.impl;

import com.digipay.wallet.exceptions.BaseException;
import com.digipay.wallet.models.dto.MoneyTransfer;
import com.digipay.wallet.models.entity.TransactionEntity;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import com.digipay.wallet.models.enums.WalletStatus;
import com.digipay.wallet.models.enums.TransactionType;
import com.digipay.wallet.repositories.TransactionRepository;
import com.digipay.wallet.services.TransactionService;
import com.digipay.wallet.services.UserService;
import com.digipay.wallet.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TransactionServiceImpl implements TransactionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final WalletService walletService;

    private UserEntity getUsernameBySecurityContextHolder() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userService.findByUsername(username);
        return user;
    }


    @Override
    @Transactional
    public void chargeWallet(TransactionEntity transaction) {
        LOGGER.info("saving new transaction:{} to db", transaction);
        UserEntity user = getUsernameBySecurityContextHolder();
        String walletName = transaction.getWallet().getWalletName();
        WalletEntity wallet = walletService.findByWalletName(walletName);
        transaction.setUser(user);
        transaction.setWallet(wallet);
        save(transaction);
        deposit(transaction);
    }

    @Override
    public void save(TransactionEntity transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void deposit(TransactionEntity transaction) throws BaseException {
        LOGGER.info("charge wallet");
        Double amount = transaction.getAmount();
        UserEntity user = getUsernameBySecurityContextHolder();
        transaction.setUser(user);
        transaction.setAmount(0.0);
        transaction.setType(TransactionType.DEPOSIT);
        transactionRepository.save(transaction);
        if (transaction.getWallet().getStatus() == WalletStatus.ACTIVE) {
            transaction.getWallet().setBalance(transaction.getWallet().getBalance() + amount);
            walletService.save(transaction.getWallet());
        } else throw new BaseException("input.is.not.valid");
        LOGGER.error("input parameter is not valid:{}", transaction);
    }
    @Override
    @Transactional
    public void withdraw(TransactionEntity transaction) {
        LOGGER.info("withdraw wallet:{} to db", transaction);
        WalletEntity walletEntity = transaction.getWallet();
        if (walletEntity.getWalletName() == null)
            throw new BaseException("wallet.does.not.exist");
        else {
            Double amount = transaction.getAmount();
            UserEntity user = getUsernameBySecurityContextHolder();
            transaction.setUser(user);
            String walletName = walletEntity.getWalletName();
            WalletEntity wallet = walletService.findByWalletName(walletName);
            WalletStatus status = wallet.getStatus();
            Integer version = wallet.getVersion();
            Long id = wallet.getWalletId();
            Double balance = wallet.getBalance();
            Double remainedBalance = balance - amount;
            if (status.equals(WalletStatus.ACTIVE) && balance >= 0 && amount <= balance) {
                walletEntity.setBalance(remainedBalance);
                walletEntity.setWalletId(id);
                walletEntity.setVersion(version);
                walletEntity.setStatus(WalletStatus.ACTIVE);
                walletService.save(walletEntity);
                transaction.setAmount(amount);
                transaction.setType(TransactionType.WITHDRAW);
                transaction.setWallet(wallet);
                transactionRepository.save(transaction);
            } else
                throw new BaseException("input.is.not.valid");
            LOGGER.error("input parameter is not valid:{}", walletEntity);
        }
    }

    @Override
    @Transactional
    public List<TransactionEntity> findByWallet(Long id) {
        Optional<WalletEntity> wallet = walletService.findById(id);
        return transactionRepository.findByWallet(wallet)
                .stream()
                .sorted(Comparator.comparing(TransactionEntity::getCreateDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void moneyTransfer(MoneyTransfer moneyTransfer) {
        UserEntity user = getUsernameBySecurityContextHolder();

        WalletEntity wallet1 = walletService.findByWalletName(moneyTransfer.getSanderWallet());
        WalletEntity wallet2 = walletService.findByWalletName(moneyTransfer.getReceiverWallet());

        if (wallet1.getUser().equals(user) && wallet2.getUser().equals(user)) {

            TransactionEntity tr1 = new TransactionEntity();
            tr1.setWallet(wallet1);
            tr1.setType(TransactionType.WITHDRAW);
            tr1.setDescription("Sent to Other Wallet" + moneyTransfer.getSanderWallet());
            tr1.setAmount(moneyTransfer.getAmount());
            withdraw(tr1);

            TransactionEntity tr2 = new TransactionEntity();
            tr2.setWallet(wallet2);
            tr2.setType(TransactionType.DEPOSIT);
            tr2.setDescription("Received to Other Wallet" + moneyTransfer.getReceiverWallet());
            tr2.setAmount(moneyTransfer.getAmount());
            chargeWallet(tr2);
        }
    }
}
