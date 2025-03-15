package com.solohicker.solo_hicker.controller;

import com.solohicker.solo_hicker.dto.request.UserLoginDto;
import com.solohicker.solo_hicker.dto.request.UserRegisterDto;
import com.solohicker.solo_hicker.service.UserService;
import com.solohicker.solo_hicker.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> register(@RequestBody UserRegisterDto dto){
        userService.register(dto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Registration Successfully",null),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> login(@RequestBody UserLoginDto dto){
        return new ResponseEntity<>(
                new StandardResponse(200,"Login Successfully",userService.login(dto)),
                HttpStatus.OK
        );
    }
}
