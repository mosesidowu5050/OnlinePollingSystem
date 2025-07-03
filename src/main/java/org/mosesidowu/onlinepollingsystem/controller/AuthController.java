package org.mosesidowu.onlinepollingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.onlinepollingsystem.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @GetMapping("/status")
    public ResponseEntity<String> getAuthStatus() {
        String googleLoginUrl = "/oauth2/authorization/google";
        return ResponseEntity.ok("Authentication service is running. " +
                "To initiate Google OAuth login, redirect to: " + googleLoginUrl);
    }

}
