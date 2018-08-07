package com.dkt.services;

import com.dkt.models.AppUser;
import com.dkt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // in createAuthority, must include "ROLE_" in front of role name.
//        System.out.println("Search user from DB...");
        AppUser user = Optional.ofNullable(userRepository.findUserByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!user.isActive()){
            throw new UsernameNotFoundException("User: " + user.getUsername() +" not active");
        }

//        System.out.println(user.getUsername());

        List<GrantedAuthority> authorities;

//        System.out.println("granting...");

        if(user.isAdmin()){
            if(user.getUsername().equals("root")){
                authorities = AuthorityUtils.createAuthorityList("ROLE_ROOT");
            }else {
                authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
            }
        }else {
            authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        }

//        authorities = user.isAdmin() ? user.getUsername().equals("root") ? AuthorityUtils.createAuthorityList("ROLE_ROOT") : AuthorityUtils.createAuthorityList("ROLE_ADMIN") : AuthorityUtils.createAuthorityList("ROLE_USER");

        return new User(user.getUsername(), user.getPassword(), authorities );
    }

}
