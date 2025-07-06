package org.mosesidowu.onlinepollingsystem.controller;

import lombok.RequiredArgsConstructor;

import org.mosesidowu.onlinepollingsystem.dtos.responses.UserResponseDTO;
import org.mosesidowu.onlinepollingsystem.security.JwtTokenProvider;
import org.mosesidowu.onlinepollingsystem.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final IUserService userService;


    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        UserResponseDTO user = userService.findById(userId);
        System.out.println(user);
        return ResponseEntity.ok(user);
    }



    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logged out (frontend must clear token)");
    }
}
