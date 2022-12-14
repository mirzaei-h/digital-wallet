package com.digipay.wallet.mappers;

import com.digipay.wallet.models.dto.UserDto;
import com.digipay.wallet.models.dto.WalletDto;
import com.digipay.wallet.models.dto.WalletDtoWithoutBalance;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T16:40:29+0330",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
@Component
public class WalletMapperImpl implements WalletMapper {

    @Override
    public WalletEntity dtoToEntityConvertor(WalletDto walletDto) {
        if ( walletDto == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setVersion( walletDto.getVersion() );
        walletEntity.setCreateDate( walletDto.getCreateDate() );
        walletEntity.setUpdateDate( walletDto.getUpdateDate() );
        walletEntity.setWalletId( walletDto.getWalletId() );
        walletEntity.setWalletName( walletDto.getWalletName() );
        walletEntity.setBalance( walletDto.getBalance() );
        walletEntity.setStatus( walletDto.getStatus() );
        walletEntity.setUser( userDtoToUserEntity( walletDto.getUser() ) );

        return walletEntity;
    }

    @Override
    public List<WalletDto> entityToDtoConvertor(List<WalletEntity> tList) {
        if ( tList == null ) {
            return null;
        }

        List<WalletDto> list = new ArrayList<WalletDto>( tList.size() );
        for ( WalletEntity walletEntity : tList ) {
            list.add( walletEntityToWalletDto( walletEntity ) );
        }

        return list;
    }

    @Override
    public WalletEntity convertDtoWithoutBalanceToEntity(WalletDtoWithoutBalance walletDtoWithoutBalance) {
        if ( walletDtoWithoutBalance == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setVersion( walletDtoWithoutBalance.getVersion() );
        walletEntity.setCreateDate( walletDtoWithoutBalance.getCreateDate() );
        walletEntity.setUpdateDate( walletDtoWithoutBalance.getUpdateDate() );
        walletEntity.setWalletId( walletDtoWithoutBalance.getWalletId() );
        walletEntity.setWalletName( walletDtoWithoutBalance.getWalletName() );
        walletEntity.setStatus( walletDtoWithoutBalance.getStatus() );

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
}
