package com.tradingengine.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class UserEntity {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
}
