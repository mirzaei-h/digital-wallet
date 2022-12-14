package com.digipay.wallet.services;

import com.digipay.wallet.base.BaseService;
import com.digipay.wallet.models.entity.UserEntity;

public interface UserService extends BaseService<UserEntity, Long> {
    UserEntity findByUsername(String username);
    void update(UserEntity user);

}
