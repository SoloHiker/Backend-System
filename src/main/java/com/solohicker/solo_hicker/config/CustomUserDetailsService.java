package com.solohicker.solo_hicker.config;

import com.solohicker.solo_hicker.entity.User;
import com.solohicker.solo_hicker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User selectedUser = userRepository.findByUsername(username);

        if(Objects.isNull(selectedUser)){
            System.out.println("User not founded");
            throw new UsernameNotFoundException("User not founded");
        }

        return new CustomUserDetails(selectedUser);
    }
}
