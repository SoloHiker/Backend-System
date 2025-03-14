package com.solohicker.solo_hicker.controller;

import com.solohicker.solo_hicker.dto.RegisterDto;
import com.solohicker.solo_hicker.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto){
        userService.register(registerDto);
    }
}
