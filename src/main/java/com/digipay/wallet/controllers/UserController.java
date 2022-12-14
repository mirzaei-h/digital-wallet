package com.digipay.wallet.controllers;

import com.digipay.wallet.exceptions.BaseException;
import com.digipay.wallet.mappers.UserMapper;
import com.digipay.wallet.models.dto.UserDto;
import com.digipay.wallet.models.dto.UserResponseDto;
import com.digipay.wallet.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping
    public void save(@Valid @RequestBody UserDto userDto) throws BaseException {
        userService.save(userMapper.dtoToEntityConvertor(userDto));
    }

    @PutMapping
    public void updateUser(@Valid @RequestBody UserDto userDto) throws BaseException {
        userService.update(userMapper.dtoToEntityConvertor(userDto));
    }


}
