package com.tradingengine.userservice.service.impl;

import com.tradingengine.userservice.dto.UserResponseDto;
import com.tradingengine.userservice.entity.UserEntity;
import com.tradingengine.userservice.mapper.UserResponseDtoMapper;
import com.tradingengine.userservice.repository.UserRepository;
import com.tradingengine.userservice.service.UserService;
import com.tradingengine.userservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserResponseDtoMapper mapper;

    @Override
    public void authenticateUser(UUID userId, String name, String email) {
        Optional<UserEntity> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            String[]  splitName = name.split(" ");
            UserEntity newUser = UserEntity.builder()
                    .userId(userId)
                    .firstName(splitName[0])
                    .lastName(splitName[1])
                    .email(email)
                    .build();
            userRepository.save(newUser);
        }
    }

    @Override
    public UserResponseDto getUser(UUID userId) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(userId);
        return mapper.apply(
                user.orElseThrow(
                        () -> new UserNotFoundException(userId)
                )
        );
    }
}
