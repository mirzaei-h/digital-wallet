package com.digipay.wallet.services.impl;

import com.digipay.wallet.WalletApplication;
import com.digipay.wallet.models.entity.TransactionEntity;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import com.digipay.wallet.models.enums.TransactionType;
import com.digipay.wallet.models.enums.WalletStatus;
import com.digipay.wallet.repositories.TransactionRepository;
import com.digipay.wallet.repositories.UserRepository;
import com.digipay.wallet.repositories.WalletRepository;
import com.digipay.wallet.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
@EnableTransactionManagement
class TransactionServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;


    @BeforeEach
    void init(){

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("user", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    void doesDepositTransaction(){
        UserEntity user2 = new UserEntity();
        user2.setUsername("username2");
        user2.setPassword("Password");
        userRepository.save(user2);

        WalletEntity wallet2 = new WalletEntity();
        wallet2.setWalletName("w10");
        wallet2.setBalance(100.0);
        wallet2.setStatus(WalletStatus.ACTIVE);
        walletRepository.save(wallet2);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountNumber("accountNumber2");
        transaction.setAmount(30.0);
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setDescription("done");
        transaction.setWallet(wallet2);
        transactionService.deposit(transaction);

        assertEquals(walletRepository.findByWalletName("w10").getBalance(),130.0);
        assertTrue(transactionRepository.findAll().size() == 1);

    }



    @Test
    void doesWithdrawTransaction(){
        UserEntity user2 = new UserEntity();
        user2.setUsername("username2");
        user2.setPassword("Password");
        userRepository.save(user2);

        WalletEntity wallet2 = new WalletEntity();
        wallet2.setWalletName("w10");
        wallet2.setBalance(100.0);
        wallet2.setStatus(WalletStatus.ACTIVE);
        walletRepository.save(wallet2);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountNumber("accountNumber2");
        transaction.setAmount(30.0);
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setDescription("done");
        transaction.setWallet(wallet2);
        transactionService.withdraw(transaction);

        assertEquals(walletRepository.findByWalletName("w10").getBalance(),70.0);
        assertTrue(transactionRepository.findAll().size() == 1);

    }

}