package io.github.rnetai.demo.service;

import io.github.rnetai.demo.external.ai.Gemini2_5FlashLite;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final Gemini2_5FlashLite geminiModel;
    private final RestTemplate restTemplate;

    public GeminiService(Gemini2_5FlashLite geminiModel, RestTemplate restTemplate) {
        this.geminiModel = geminiModel;
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> generateContent(HttpServletRequest request, String prompt) {
        Map<String, Object> requestBody = buildPayload(prompt);
        return geminiModel.call(request, restTemplate, requestBody);
    }

    private Map<String, Object> buildPayload(String prompt) {
        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(textPart));

        Map<String, Object> body = new HashMap<>();
        body.put("contents", List.of(content));

        return body;
    }
}
