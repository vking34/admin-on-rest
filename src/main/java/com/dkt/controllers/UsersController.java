package com.dkt.controllers;


import com.dkt.models.AppUser;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")

public class UsersController {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users by page
    @GetMapping("/")
    public Page<AppUser> getUsers(@RequestParam int page){
        System.out.println("GET: All users");
        PageRequest pageRequest = new PageRequest(page, 10);

//        return userRepository.findAll(pageRequest);
        return userRepository.findAllNotCludingPassword(pageRequest);
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

    // get user by username
    @GetMapping("/{id}")
    public AppUser getOneUser(@PathVariable String id){
        System.out.println("GET: One User " + id);
        ObjectId objectId = new ObjectId(id);
        return userRepository.findUserByUsernameNotCludingPassword(objectId);
    }


    // delete user
    @DeleteMapping("/{id}")
    public resp deleteUser(@PathVariable String id){
        System.out.println("DELETE: user " + id);
        userRepository.deleteUserById(id);
        return new resp(true);
    }

    // update user
    @PutMapping("/{id}")
    public resp updateUser(@PathVariable String id,@RequestBody Map<String, String> info){
        System.out.println("UPDATE: user " + id);
        AppUser appUser = userRepository.findUserById(id);
        System.out.println(info.toString());

        if(appUser != null){
            try{
                String newPass = info.get("password");
                if(newPass != null){
                    if(!newPass.equals(appUser.getPassword())){
                        appUser.setPassword(passwordEncoder.encode(newPass));
                    }
                }

            }
            catch (Exception e){
                System.out.println(e.getMessage());
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



    // search users
    @GetMapping("/filter/")
    public Page<AppUser> getUserByUsername(@RequestParam("username") String username, @RequestParam("page") int page){
        System.out.println("Filter according to " + username);
//        List<AppUser> users = userRepository.findUsersByUsername(username);
//        JSONObject result = new JSONObject();
//        result.put("data", users);
//        result.put("total", users.size());
        PageRequest pageRequest = new PageRequest(page, 10);

        return userRepository.findUsersByUsername(username, pageRequest);
    }

}
