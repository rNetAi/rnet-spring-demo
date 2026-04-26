package io.github.rnetai.demo.service;

import io.github.rnetai.demo.dto.AuthTokenResponse;
import io.github.rnetai.demo.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public AuthTokenResponse generateToken(String username) {
        String token = jwtUtil.generateToken(username);
        return new AuthTokenResponse(token, "Bearer", "24h");
    }
}
