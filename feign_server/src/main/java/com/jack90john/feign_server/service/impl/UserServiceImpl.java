package com.jack90john.feign_server.service.impl;

import com.jack90john.feign_server.entity.User;
import com.jack90john.feign_server.repository.UserRepository;
import com.jack90john.feign_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Designer: jack
 * Date: 2019-01-25
 * Version: 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        password = "{bcrypt}" + passwordEncoder.encode(password);
        user.setPassword(password);
        return userRepository.save(user);
    }
}

