package io.github.rnetai.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class GeminiRequest {

    @NotBlank(message = "Prompt must not be empty")
    private String prompt;

    public GeminiRequest() {}
    public GeminiRequest(String prompt) { this.prompt = prompt; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}