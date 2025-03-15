package com.solohicker.solo_hicker.service.impl;

import com.solohicker.solo_hicker.dto.request.UserLoginDto;
import com.solohicker.solo_hicker.dto.request.UserRegisterDto;
import com.solohicker.solo_hicker.entity.User;
import com.solohicker.solo_hicker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService implements com.solohicker.solo_hicker.service.UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserRegisterDto dto) {
        User newUser = new User(
                UUID.randomUUID().toString(),
                dto.getUsername(),
                dto.getPassword()
        );

        userRepository.save(newUser);
    }

    @Override
    public String login(UserLoginDto dto) {
        var selectedUser = userRepository.findByUsername(dto.getUsername());

        if(!Objects.isNull(selectedUser))
            return "success";
        return "failure";
    }
}
