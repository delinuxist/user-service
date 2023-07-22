package com.tradingengine.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReportDto {
     private UUID userId;
     private String firstName;
     private String lastName;
     private String email;
     private String command;
     private String timestamp;
}
