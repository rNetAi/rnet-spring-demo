package io.github.rnetai.demo.controller;

import io.github.rnetai.demo.dto.ApiResponse;
import io.github.rnetai.demo.dto.AuthRequest;
import io.github.rnetai.demo.dto.AuthTokenResponse;
import io.github.rnetai.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<AuthTokenResponse>> generateToken(
            @Valid @RequestBody AuthRequest authRequest
    ) {
        AuthTokenResponse tokenResponse = authService.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(ApiResponse.success(tokenResponse));
    }
}
