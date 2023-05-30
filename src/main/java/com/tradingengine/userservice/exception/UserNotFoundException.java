package com.tradingengine.userservice.exception;

import java.util.UUID;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(UUID userId) {
        super("User with id:" + userId +" not found");
    }
}
