package com.study.profanityFilterSystem.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}
//
//public record ChatGPTRequest(String model, String prompt,
//                             double temperaturem, int max_tokens) {
//    public static ChatGPTRequest creat(String prompt) {
//        return new ChatGPTRequest("text-davinci-003", prompt, 1, 100);
//    }
//}
