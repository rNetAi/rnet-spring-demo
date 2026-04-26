package io.github.rnetai.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank(message = "Username must not be empty")
    private String username;

    public AuthRequest() {}
    public AuthRequest(String username) { this.username = username; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
