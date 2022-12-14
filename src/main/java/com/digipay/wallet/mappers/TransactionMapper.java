package com.digipay.wallet.mappers;

import com.digipay.wallet.models.dto.TransactionDto;
import com.digipay.wallet.models.entity.TransactionEntity;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TransactionMapper  {

    @Mapping(target = "wallet.walletName", source = "walletName")
    TransactionEntity  dtoToEntityConvertor(TransactionDto transactionDto);
    List<TransactionDto> entityToDtoConvertor(List<TransactionEntity> tList);


}
