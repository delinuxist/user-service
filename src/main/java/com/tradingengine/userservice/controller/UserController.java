package com.tradingengine.userservice.controller;

import com.tradingengine.userservice.dto.UserResponseDto;
import com.tradingengine.userservice.service.UserService;
import com.tradingengine.userservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/auth")
    public void authenticate(@AuthenticationPrincipal Jwt jwt) {
        userService.authenticateUser(UUID.fromString(jwt.getSubject()),jwt.getClaims().get("name").toString(),jwt.getClaims().get("email").toString());
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDto> getUser(@AuthenticationPrincipal Jwt jwt) throws UserNotFoundException {
      return ResponseEntity.ok(
              userService.getUser(
                      UUID.fromString(jwt.getSubject()
                      )
              )
      );
    }

}
