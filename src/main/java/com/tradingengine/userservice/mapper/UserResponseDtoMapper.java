package com.tradingengine.userservice.mapper;

import com.tradingengine.userservice.dto.UserResponseDto;
import com.tradingengine.userservice.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserResponseDtoMapper implements Function<UserEntity, UserResponseDto> {
    @Override
    public UserResponseDto apply(UserEntity userEntity) {
        return UserResponseDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();
    }
}
