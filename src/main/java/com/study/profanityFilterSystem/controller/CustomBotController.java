package com.study.profanityFilterSystem.controller;

import com.study.profanityFilterSystem.dto.ChatGPTRequest;
import com.study.profanityFilterSystem.dto.ChatGPTResponse;
import com.study.profanityFilterSystem.entity.Dialog;
import com.study.profanityFilterSystem.entity.Users;
import com.study.profanityFilterSystem.service.ChatService;
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

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam(name = "nickname") String nickname, @RequestParam(name = "prompt") String prompt) {
        // 사용자 가져오기 또는 생성
        Users user = chatService.getOrCreateUser(nickname);

        // GPT 요청 생성
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);
        String responseContent = chatGPTResponse.getChoices().get(0).getMessage().getContent();

        // 사용자 대화 저장
        chatService.saveDialog(user.getUserID(), 1L, prompt);

        // GPT 응답 저장
        chatService.saveDialog(1L, user.getUserID(), responseContent);

        // 응답 반환
        Map<String, String> response = new HashMap<>();
        response.put("response", responseContent);

        return response;
    }



}
