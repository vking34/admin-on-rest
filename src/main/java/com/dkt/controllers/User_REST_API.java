package com.dkt.controllers;


import com.dkt.App;
import com.dkt.models.AppUser;
import com.dkt.repositories.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest-api/users")
public class User_REST_API {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<AppUser> getUsers(){
        List<AppUser> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/{username}")
    public AppUser getUserByUsername(@PathVariable String username){
        AppUser user = userRepository.findUserByUsername(username);
        return user;
    }
}
