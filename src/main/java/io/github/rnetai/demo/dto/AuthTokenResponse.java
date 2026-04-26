package io.github.rnetai.demo.dto;

public class AuthTokenResponse {

    private String token;
    private String type;
    private String expiresIn;

    public AuthTokenResponse() {}
    public AuthTokenResponse(String token, String type, String expiresIn) {
        this.token = token;
        this.type = type;
        this.expiresIn = expiresIn;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getExpiresIn() { return expiresIn; }
    public void setExpiresIn(String expiresIn) { this.expiresIn = expiresIn; }
}
