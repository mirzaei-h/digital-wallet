package com.digipay.wallet.services.impl;

import com.digipay.wallet.WalletApplication;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import com.digipay.wallet.models.enums.WalletStatus;
import com.digipay.wallet.repositories.UserRepository;
import com.digipay.wallet.repositories.WalletRepository;
import com.digipay.wallet.services.UserService;
import com.digipay.wallet.services.WalletService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
@EnableTransactionManagement
class WalletServiceImplTest {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void init() {
        UserEntity user1 = new UserEntity(1L,"username","Password", null, null);
        userRepository.save(user1);
        WalletEntity wallet = new WalletEntity(1L, "w2", 0.0, WalletStatus.ACTIVE, user1,null );
        walletRepository.save(wallet);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("user", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    @DisplayName("Test saveUser method")
    void testSaveWallet() {
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("1234");
        userService.save(user);

        WalletEntity wallet = new WalletEntity();
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setUser(user);
        wallet.setWalletName("w1");
        walletService.save(wallet);
        WalletEntity walletEntity = walletRepository.findByWalletName("w1");

        assertTrue(walletRepository.findAll().size() == 2);
        assertEquals(wallet.getWalletName(),walletEntity.getWalletName());
        assertThat(walletEntity).isNotNull();
    }

    @Test
    @DisplayName("Test saveWallet method with duplicate walletName")
    void testSaveWallet2() {
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("1234");
        userService.save(user);

        WalletEntity wallet = new WalletEntity();
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setUser(user);
        wallet.setWalletName("w2");
        walletService.save(wallet);

        assertThrows(RuntimeException.class, () -> walletService.save(walletRepository.findByWalletName("w2")));
    }

    @Test
    @DisplayName("checking id found in user document")
    public void findById() {

        assertTrue(walletService.findById(1L).isPresent(), "id found");
    }

    @Test
    @DisplayName("throw exception when username is null")
    public void WalletEntityIsNullThrowException() {
        WalletEntity wallet = walletService.findByWalletName("");
        assertThrows(Exception.class, (Executable) wallet, "should throw exception");
    }
    @Test
    @DisplayName("wallet service is working well!")
    public void whenContextLoadsThenServiceISNotNull() {
        assertThat(walletService).isNotNull();
    }

    @Test
    @DisplayName("checking id found in user document")
    public void findByWalletName() {
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("1234");
        userService.save(user);

        WalletEntity wallet = new WalletEntity();
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setUser(user);
        wallet.setWalletName("w10");
        walletService.save(wallet);

        assertThat(walletService.findByWalletName("w10").equals( wallet));
    }





}