package com.digipay.wallet.services.impl;

import com.digipay.wallet.WalletApplication;
import com.digipay.wallet.exceptions.BaseException;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.repositories.UserRepository;
import com.digipay.wallet.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void init() {
        UserEntity user1 = new UserEntity();
        user1.setUsername("username");
        user1.setPassword("Password");
        userService.save(user1);
        userRepository.save(user1);

        UserEntity user2 = new UserEntity();
        user2.setUsername("username2");
        user2.setPassword("Password");
        userService.save(user2);
        userRepository.save(user2);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("user", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    @DisplayName("Test saveUser method")
    void testSaveUser() {
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("1234");
        userService.save(user);
        UserEntity savedUser = userRepository.findByUsername("user");

        assertTrue(userRepository.findAll().size() == 3);
        assertTrue(passwordEncoder.matches("1234", savedUser.getPassword()));
        assertEquals(user.getUsername(),savedUser.getUsername());
        assertThat(savedUser).isNotNull();
    }

    @Test
    @DisplayName("Test saveUser method with duplicate username")
    void testSaveUser2() {
        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword("1234");

        assertThrows(RuntimeException.class, () -> userService.save(user));
    }

    @Test
    @DisplayName("Test saveUser method with null password")
    void SaveNullPassword() {
        UserEntity user1 = new UserEntity();
        user1.setUsername("user");
        assertThrows(RuntimeException.class, () -> userService.save(user1));
    }

}