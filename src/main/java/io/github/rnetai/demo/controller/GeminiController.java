package io.github.rnetai.demo.controller;

import io.github.rNetAi.rnetCore.scanner.annotations.RNetEndpoint;
import io.github.rnetai.demo.dto.ApiResponse;
import io.github.rnetai.demo.dto.GeminiRequest;
import io.github.rnetai.demo.external.ai.Gemini2_5FlashLite;
import io.github.rnetai.demo.service.GeminiService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @RNetEndpoint(
            path = "/generate",
            method = RequestMethod.POST,
            usageResources = {Gemini2_5FlashLite.class}
    )
    public ResponseEntity<ApiResponse<Map<String, Object>>> generate(
            @Valid @RequestBody GeminiRequest geminiRequest,
            HttpServletRequest request
    ) {
        Map<String, Object> response = geminiService.generateContent(
                request,
                geminiRequest.getPrompt()
        );
        // use gemini response
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
