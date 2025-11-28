package com.ll.security_demo.service;

import com.ll.security_demo.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);

    boolean existsByUsername(String username);
}

