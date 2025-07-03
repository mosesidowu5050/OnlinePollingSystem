package org.mosesidowu.onlinepollingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.onlinepollingsystem.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getAuthStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Authentication service is operational.");
        response.put("googleLoginUrl", "/oauth2/authorization/google");
        response.put("logoutUrl", "/api/auth/logout");

        return ResponseEntity.ok(response);
    }


    @GetMapping("/google-login-url")
    public ResponseEntity<Map<String, String>> getGoogleLoginUrl() {
        Map<String, String> response = new HashMap<>();
        response.put("url", "/oauth2/authorization/google");
        response.put("description", "Login through your Google email.");

        return ResponseEntity.ok(response);
    }
}
