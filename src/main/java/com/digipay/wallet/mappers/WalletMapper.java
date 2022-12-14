package com.digipay.wallet.mappers;

import com.digipay.wallet.models.dto.WalletDto;
import com.digipay.wallet.models.dto.WalletDtoWithoutBalance;
import com.digipay.wallet.models.entity.WalletEntity;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletEntity dtoToEntityConvertor(WalletDto walletDto);
    List<WalletDto> entityToDtoConvertor(List<WalletEntity> tList);
    WalletEntity convertDtoWithoutBalanceToEntity(WalletDtoWithoutBalance walletDtoWithoutBalance);

}
