package com.solohicker.solo_hicker.service;

import com.solohicker.solo_hicker.dto.request.UserLoginDto;
import com.solohicker.solo_hicker.dto.request.UserRegisterDto;

public interface UserService {
    void register(UserRegisterDto dto);
    String login(UserLoginDto dto);
}
