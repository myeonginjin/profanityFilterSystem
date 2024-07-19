package com.study.profanityFilterSystem.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.profanityFilterSystem.dto.ChatGPTRequest;
import com.study.profanityFilterSystem.dto.ChatGPTResponse;

import java.net.URI;
//
//public class ChatGPTService {
//
//
//    public String chat(String prompt) throws  Exception {
//        var objectMapper = new ObjectMapper();
//
//        var chatGptRequest = ChatGPTRequest.creat(prompt);
//        var requestBody = objectMapper.writeValueAsString(chatGptRequest);
//        var responseBody = sendRequest(requestBody);
//        var chatGptResponse = objectMapper.readValue(responseBody, ChatGPTResponse.class);
//
//        return chatGptResponse.getText().orElseThrow();
//    }
//
//    private String sendRequest(String requestBody) throws Exception{
//        URI uri = URI.create(apiUrl);
//    }
//}
