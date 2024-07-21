package com.study.profanityFilterSystem.controller;

import com.study.profanityFilterSystem.dto.ChatGPTRequest;
import com.study.profanityFilterSystem.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bot")
public class CustomBotController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam(name = "prompt") String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);

        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);

        String responseContent = chatGPTResponse.getChoices().get(0).getMessage().getContent();

        Map<String, String> response = new HashMap<>();
        response.put("response", responseContent);

        return response;
    }
}
