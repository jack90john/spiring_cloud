package com.jack90john.feign_server.service;

import com.jack90john.feign_server.entity.User;

public interface UserService {
    User create(String username, String password);
}
