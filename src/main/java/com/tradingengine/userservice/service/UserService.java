package com.tradingengine.userservice.service;


import com.tradingengine.userservice.dto.UserResponseDto;
import com.tradingengine.userservice.exception.UserNotFoundException;

import java.util.UUID;

public interface UserService {

    void authenticateUser(UUID clientId,String name,String email);

    UserResponseDto getUser(UUID clientId) throws UserNotFoundException;
}
