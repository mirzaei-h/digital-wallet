package com.digipay.wallet.base;

import java.util.List;

public interface BaseMapper<T extends BaseEntity, D extends BaseDto>{

    T  dtoToEntityConvertor(D d);
    D  entityToDtoConvertor(T t);
    List<T>  dtoToEntityConvertor (List<D> dList);
    List<D> entityToDtoConvertor(List<T> tList);

}
