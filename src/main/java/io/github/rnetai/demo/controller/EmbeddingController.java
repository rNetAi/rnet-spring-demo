package io.github.rnetai.demo.controller;

import io.github.rNetAi.rnetCore.scanner.annotations.RNetEndpoint;
import io.github.rnetai.demo.dto.ApiResponse;
import io.github.rnetai.demo.external.ai.Gemini2_5FlashLite;
import io.github.rnetai.demo.external.ai.TextEmbedding3Small;
import io.github.rnetai.demo.service.EmbeddingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/embedding")
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    public EmbeddingController(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @CrossOrigin("*")
    @RNetEndpoint(
            path = "/test",
            method = RequestMethod.GET,
//            usageResources =  {TextEmbedding3Small.class, Gemini2_5FlashLite.class}, // both pass for Ai model expired in 15 sec.
            usageResources = {TextEmbedding3Small.class}
    )
    public ResponseEntity<ApiResponse<Map<String, Object>>> testEmbedding(
            HttpServletRequest request
    ) {
        Map<String, Object> response = embeddingService.generateEmbedding(request);
        // user embedding
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
