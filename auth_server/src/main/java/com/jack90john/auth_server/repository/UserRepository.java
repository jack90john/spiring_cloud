package com.jack90john.auth_server.repository;

import com.jack90john.auth_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * Designer: jack
 * Date: 2019-01-25
 * Version: 1.0.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
