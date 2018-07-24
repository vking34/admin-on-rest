package com.dkt.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String args[]){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("root"));
        System.out.println(passwordEncoder.encode("admin"));
        System.out.println(passwordEncoder.encode("test"));
    }

}
