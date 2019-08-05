package com.lc.cloud.controller;

import com.lc.cloud.entity.User;
import com.lc.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller.
 *
 * @author zyz.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/customers/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }
}
