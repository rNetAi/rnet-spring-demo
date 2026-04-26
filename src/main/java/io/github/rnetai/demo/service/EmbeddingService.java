package io.github.rnetai.demo.service;

import io.github.rnetai.demo.external.ai.TextEmbedding3Small;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EmbeddingService {

    private final TextEmbedding3Small embeddingModel;
    private final RestTemplate restTemplate;

    public EmbeddingService(TextEmbedding3Small embeddingModel, RestTemplate restTemplate) {
        this.embeddingModel = embeddingModel;
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> generateEmbedding(HttpServletRequest request) {
        Map<String, Object> requestBody = Map.of(
                "input", "Hello to rNet ai",
                "model", "text-embedding-3-small"
        );
        return embeddingModel.call(request, restTemplate, requestBody);
    }
}
