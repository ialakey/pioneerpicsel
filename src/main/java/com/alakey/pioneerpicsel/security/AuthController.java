package com.alakey.pioneerpicsel.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.authenticate(request.getEmailOrPhone(), request.getPassword());
    }

    @Data
    public static class LoginRequest {
        private String emailOrPhone;
        private String password;
    }
}
