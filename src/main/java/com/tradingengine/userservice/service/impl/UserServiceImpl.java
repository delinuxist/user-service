package com.tradingengine.userservice.service.impl;

import com.tradingengine.userservice.dto.UserReportDto;
import com.tradingengine.userservice.dto.UserResponseDto;
import com.tradingengine.userservice.entity.UserEntity;
import com.tradingengine.userservice.mapper.UserResponseDtoMapper;
import com.tradingengine.userservice.producer.RabbitMqProducer;
import com.tradingengine.userservice.repository.UserRepository;
import com.tradingengine.userservice.service.UserService;
import com.tradingengine.userservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserResponseDtoMapper mapper;

    private final RabbitMqProducer rabbitMqProducer;

    @Override
    public void authenticateUser(UUID userId, String name, String email) {
        UserEntity user;

        if(userRepository.findById(userId).isEmpty()) {
            String[]  splitName = name.split(" ");
            user = UserEntity.builder()
                    .userId(userId)
                    .firstName(splitName[0])
                    .lastName(splitName[1])
                    .email(email)
                    .build();
            userRepository.save(user);
        } else {
            user = userRepository.findById(userId).get();
        }
        UserReportDto userReport = UserReportDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .command("Logged In")
                .timestamp(LocalDateTime.now().toString())
                .build();
        rabbitMqProducer.sendMessage(userReport);
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
