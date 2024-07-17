package com.study.profanityFilterSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Message {
    private String role;
    private String content;

    public Message() {
        // 기본 생성자
    }

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
