package com.digipay.wallet.mappers;

import com.digipay.wallet.models.dto.TransactionDto;
import com.digipay.wallet.models.dto.UserDto;
import com.digipay.wallet.models.dto.UserResponseDto;
import com.digipay.wallet.models.dto.WalletDto;
import com.digipay.wallet.models.entity.TransactionEntity;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T16:40:30+0330",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity dtoToEntityConvertor(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setVersion( userDto.getVersion() );
        userEntity.setCreateDate( userDto.getCreateDate() );
        userEntity.setUpdateDate( userDto.getUpdateDate() );
        userEntity.setUserId( userDto.getUserId() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setPassword( userDto.getPassword() );

        return userEntity;
    }

    @Override
    public UserResponseDto entityToDtoConvertor(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setVersion( userEntity.getVersion() );
        userResponseDto.setCreateDate( userEntity.getCreateDate() );
        userResponseDto.setUpdateDate( userEntity.getUpdateDate() );
        userResponseDto.setUserId( userEntity.getUserId() );
        userResponseDto.setUsername( userEntity.getUsername() );
        userResponseDto.setWallets( walletEntityListToWalletDtoList( userEntity.getWallets() ) );
        userResponseDto.setTransactions( transactionEntityListToTransactionDtoList( userEntity.getTransactions() ) );

        return userResponseDto;
    }

    protected UserDto userEntityToUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setVersion( userEntity.getVersion() );
        userDto.setCreateDate( userEntity.getCreateDate() );
        userDto.setUpdateDate( userEntity.getUpdateDate() );
        userDto.setUserId( userEntity.getUserId() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setPassword( userEntity.getPassword() );

        return userDto;
    }

    protected WalletDto walletEntityToWalletDto(WalletEntity walletEntity) {
        if ( walletEntity == null ) {
            return null;
        }

        WalletDto walletDto = new WalletDto();

        walletDto.setVersion( walletEntity.getVersion() );
        walletDto.setCreateDate( walletEntity.getCreateDate() );
        walletDto.setUpdateDate( walletEntity.getUpdateDate() );
        walletDto.setWalletId( walletEntity.getWalletId() );
        walletDto.setWalletName( walletEntity.getWalletName() );
        walletDto.setBalance( walletEntity.getBalance() );
        walletDto.setStatus( walletEntity.getStatus() );
        walletDto.setUser( userEntityToUserDto( walletEntity.getUser() ) );

        return walletDto;
    }

    protected List<WalletDto> walletEntityListToWalletDtoList(List<WalletEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<WalletDto> list1 = new ArrayList<WalletDto>( list.size() );
        for ( WalletEntity walletEntity : list ) {
            list1.add( walletEntityToWalletDto( walletEntity ) );
        }

        return list1;
    }

    protected TransactionDto transactionEntityToTransactionDto(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setVersion( transactionEntity.getVersion() );
        transactionDto.setCreateDate( transactionEntity.getCreateDate() );
        transactionDto.setUpdateDate( transactionEntity.getUpdateDate() );
        transactionDto.setTransactionId( transactionEntity.getTransactionId() );
        transactionDto.setAccountNumber( transactionEntity.getAccountNumber() );
        transactionDto.setAmount( transactionEntity.getAmount() );
        transactionDto.setType( transactionEntity.getType() );
        transactionDto.setDescription( transactionEntity.getDescription() );
        transactionDto.setUser( userEntityToUserDto( transactionEntity.getUser() ) );

        return transactionDto;
    }

    protected List<TransactionDto> transactionEntityListToTransactionDtoList(List<TransactionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<TransactionDto> list1 = new ArrayList<TransactionDto>( list.size() );
        for ( TransactionEntity transactionEntity : list ) {
            list1.add( transactionEntityToTransactionDto( transactionEntity ) );
        }

        return list1;
    }
}
