package com.solohicker.solo_hicker.service.impl;

import com.solohicker.solo_hicker.dto.RegisterDto;
import com.solohicker.solo_hicker.entity.User;
import com.solohicker.solo_hicker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements com.solohicker.solo_hicker.service.UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterDto registerDto) {
        User newUser = new User(
                UUID.randomUUID().toString(),
                registerDto.getUsername(),
                registerDto.getPassword()
        );

        userRepository.save(newUser);
    }
}
