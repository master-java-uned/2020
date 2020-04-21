package com.covid19.authservice.controller;

import com.covid19.authservice.model.User;
import com.covid19.authservice.service.UserService;
import com.covid19.common.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping
    public Iterable<User> getAll() throws DataAccessException {
        return userService.findUsers();
    }


}
