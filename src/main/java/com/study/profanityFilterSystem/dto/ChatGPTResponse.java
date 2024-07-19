package com.study.profanityFilterSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTResponse {
    private List<Choice> choices;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private int index;
        private Message message;

    }
}

//public record ChatGPTResponse(List<Choice> choices) {
//
//    public Optional<String> getText() {
//        if(choices == null || choices.isEmpty())
//            return Optional.empty();
//        return Optional.of(choices.get(0).text);
//    }
//
//    record Choice(int id, String text) {}
//}
