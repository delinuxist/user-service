package com.tradingengine.userservice.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}