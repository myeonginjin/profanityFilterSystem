package com.study.profanityFilterSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage() {
        return "home"; // home.html 파일이 templates 폴더 안에 있어야 합니다.
    }
}
