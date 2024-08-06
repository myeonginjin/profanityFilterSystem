package com.study.profanityFilterSystem.controller;

import com.study.profanityFilterSystem.entity.Dialog;
import com.study.profanityFilterSystem.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bot")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/reportWithAhoCorasick")
    public Map<String, Object> reportUserWithAhoCorasick(@RequestParam String nickname) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Dialog> evidence = chatService.getRecentDialogs(nickname);
            response.put("status", "success");
            response.put("evidence", evidence);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/reportWith1")
    public Map<String, Object> reportUserWith1(@RequestParam String nickname) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Dialog> evidence = chatService.getRecentDialogs(nickname);
            response.put("status", "success");
            response.put("evidence", evidence);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/reportWith2")
    public Map<String, Object> reportUserWith2(@RequestParam String nickname) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Dialog> evidence = chatService.getRecentDialogs(nickname);
            response.put("status", "success");
            response.put("evidence", evidence);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/reportWith3")
    public Map<String, Object> reportUserWith3(@RequestParam String nickname) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Dialog> evidence = chatService.getRecentDialogs(nickname);
            response.put("status", "success");
            response.put("evidence", evidence);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
