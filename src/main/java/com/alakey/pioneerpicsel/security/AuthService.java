package com.alakey.pioneerpicsel.security;

import com.alakey.pioneerpicsel.entity.UserInfo;
import com.alakey.pioneerpicsel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String authenticate(String emailOrPhone, String password) {
        Optional<UserInfo> userOpt = emailOrPhone.contains("@")
                ? userRepository.findByEmails_Email(emailOrPhone)
                : userRepository.findByPhones_Phone(emailOrPhone);

        UserInfo userInfo = userOpt.orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, userInfo.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(userInfo.getId());
    }
}
