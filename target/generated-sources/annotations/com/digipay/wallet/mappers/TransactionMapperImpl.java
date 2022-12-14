package com.digipay.wallet.mappers;

import com.digipay.wallet.models.dto.TransactionDto;
import com.digipay.wallet.models.dto.UserDto;
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
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionEntity dtoToEntityConvertor(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setWallet( transactionDtoToWalletEntity( transactionDto ) );
        transactionEntity.setVersion( transactionDto.getVersion() );
        transactionEntity.setCreateDate( transactionDto.getCreateDate() );
        transactionEntity.setUpdateDate( transactionDto.getUpdateDate() );
        transactionEntity.setTransactionId( transactionDto.getTransactionId() );
        transactionEntity.setAccountNumber( transactionDto.getAccountNumber() );
        transactionEntity.setAmount( transactionDto.getAmount() );
        transactionEntity.setType( transactionDto.getType() );
        transactionEntity.setDescription( transactionDto.getDescription() );
        transactionEntity.setUser( userDtoToUserEntity( transactionDto.getUser() ) );

        return transactionEntity;
    }

    @Override
    public List<TransactionDto> entityToDtoConvertor(List<TransactionEntity> tList) {
        if ( tList == null ) {
            return null;
        }

        List<TransactionDto> list = new ArrayList<TransactionDto>( tList.size() );
        for ( TransactionEntity transactionEntity : tList ) {
            list.add( transactionEntityToTransactionDto( transactionEntity ) );
        }

        return list;
    }

    protected WalletEntity transactionDtoToWalletEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setWalletName( transactionDto.getWalletName() );

        return walletEntity;
    }

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
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
}
