package com.digipay.wallet.base;

import com.digipay.wallet.exceptions.BaseException;

import java.io.Serializable;

public interface BaseService<T extends BaseEntity, ID extends Serializable> {
     void save(T t) throws BaseException;

}