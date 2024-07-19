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
    public String chat(@RequestParam(name = "prompt") String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);

        /* 요청 로그 출력
        System.out.println("Sending request to OpenAI API:");
        System.out.println(request);
         */

        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);

        /* 응답 로그 출력
        System.out.println("Received response from OpenAI API:");
        System.out.println(chatGPTResponse);
         */

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }
}
