package com.digipay.wallet.services.impl;


import com.digipay.wallet.exceptions.BaseException;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.repositories.UserRepository;
import com.digipay.wallet.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void save(UserEntity userEntity) throws BaseException {
        LOGGER.info("saving new user{} to  db", userEntity.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void update(UserEntity userEntity) {
        LOGGER.info("updating user{} to  db", userEntity.getUsername());
        if ((userEntity.getUserId() == null ))
            throw new BaseException("userId cannot be null in update");
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = findByUsername(username);
        if (user.getUserId().equals(userEntity.getUserId())){
            save(userEntity);
        }
        else throw new BaseException("only user can update username or password");
    }
    @Override
    public UserEntity findByUsername(String username) {
        LOGGER.info("fetching user{}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            LOGGER.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        } else {
            LOGGER.info("user found in the database:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

}
