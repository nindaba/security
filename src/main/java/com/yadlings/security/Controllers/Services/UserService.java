package com.yadlings.security.Controllers.Services;

import com.yadlings.security.Controllers.Domain.User;
import com.yadlings.security.Controllers.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public void initializeUsers(){
        List<User> users = Arrays.asList(
                new User(UUID.randomUUID().toString(), "A", "B", "ab", new BCryptPasswordEncoder().encode("ab12"), false),
                new User(UUID.randomUUID().toString(), "C", "D", "cd", new BCryptPasswordEncoder().encode("cd12"), true)
        );
        userRepository.saveAll(users);
    }
}
