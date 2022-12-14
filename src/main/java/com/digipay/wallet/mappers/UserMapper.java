package com.digipay.wallet.mappers;

import com.digipay.wallet.models.dto.UserDto;
import com.digipay.wallet.models.dto.UserResponseDto;
import com.digipay.wallet.models.entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity  dtoToEntityConvertor(UserDto userDto);
    UserResponseDto entityToDtoConvertor(UserEntity userEntity);


}
