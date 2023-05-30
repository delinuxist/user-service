package com.tradingengine.userservice.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String firstName,
        String lastName,
        String email
) {
}
