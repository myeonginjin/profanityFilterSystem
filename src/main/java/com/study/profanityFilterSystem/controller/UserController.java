package com.study.profanityFilterSystem.controller;

import com.study.profanityFilterSystem.entity.Users;
import com.study.profanityFilterSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Users createUser(@RequestParam String userName) {
        return userService.saveUser(userName);
    }

    @GetMapping("/{userName}")
    public Users getUser(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }
}
