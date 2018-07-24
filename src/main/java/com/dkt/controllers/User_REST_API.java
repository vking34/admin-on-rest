package com.dkt.controllers;


import com.dkt.App;
import com.dkt.models.AppUser;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class User_REST_API {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users
    @GetMapping
    public List<AppUser> getUsers(){
        List<AppUser> users = userRepository.findAll();
        return users;
    }

    // create a user
    @PostMapping
    public resp createUser(@RequestBody Map<String, String> user){
        String username = user.get("username");
        System.out.println("Creating a user: " + username);
        boolean admin;
        try {
            admin = user.get("admin").equals("true");
        }
        catch (Exception e){
            admin = false;
        }

        boolean active;
        try {
            active  = user.get("active").equals("true");
        }
        catch (Exception e){
            active = false;
        }

        AppUser appUser = new AppUser(username, passwordEncoder.encode(user.get("password")), admin, active);

        if(userRepository.findUserByUsername(username) == null){

            userRepository.insert(appUser);
            System.out.println("Inserted user: " + username);
            return new resp(true);
        }else{
            System.out.println("User exists");
            return new resp(false);
        }
    }

    // delete user
    @DeleteMapping("/{username}")
    public resp deleteUser(@PathVariable String username){

        userRepository.deleteUserByUsername(username);
        return new resp(true);
    }

    // update user
    @PutMapping("/{username}")
    public resp updateUser(@PathVariable String username,@RequestBody Map<String, String> info){

        AppUser appUser = userRepository.findUserByUsername(username);

        if(appUser != null){

            if(!info.get("password").equals(appUser.getPassword())){
                appUser.setPassword(passwordEncoder.encode(info.get("password")));
            }

            try{
                System.out.println(info.get("admin"));
                appUser.setAdmin(info.get("admin").equals("true"));
                System.out.println("isAdmin: " + appUser.isAdmin());
            }catch (Exception e){
                appUser.setAdmin(false);
            }

            try{
                System.out.println(info.get("active"));
                appUser.setActive(info.get("active").equals("true"));
                System.out.println("isActive: " + appUser.isActive());
            }catch (Exception e){
                appUser.setActive(false);
            }

            userRepository.save(appUser);
            return new resp(true);
        }
        else {
            return new resp(false);
        }
    }

    @GetMapping("/{username}")
    public AppUser getUserByUsername(@PathVariable String username){
        AppUser user = userRepository.findUserByUsername(username);
        return user;
    }

}
