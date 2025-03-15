package com.solohicker.solo_hicker.service.impl;

import com.solohicker.solo_hicker.dto.request.UserLoginDto;
import com.solohicker.solo_hicker.dto.request.UserRegisterDto;
import com.solohicker.solo_hicker.entity.User;
import com.solohicker.solo_hicker.jwt.JwtService;
import com.solohicker.solo_hicker.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService implements com.solohicker.solo_hicker.service.UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void register(UserRegisterDto dto) {
        User selectedUser = userRepository.findByUsername(dto.getUsername());

        if(!Objects.isNull(selectedUser)){
            System.out.println("User already exist");
            return;
        }

        User newUser = new User(
                UUID.randomUUID().toString(),
                dto.getUsername(),
                bCryptPasswordEncoder.encode(dto.getPassword())
        );

        userRepository.save(newUser);
    }

    @Override
    public String login(UserLoginDto dto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(), dto.getPassword()
                )
        );

//        var selectedUser = userRepository.findByUsername(dto.getUsername());

        if(authenticate.isAuthenticated()){
            User selectedUser = userRepository.findByUsername(dto.getUsername());
            return jwtService.generateJwtToken(selectedUser);
        }
        return "failure";
    }
}
