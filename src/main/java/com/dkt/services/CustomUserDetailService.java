package com.dkt.services;

import com.dkt.models.AppUser;
import com.dkt.repositories.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // in createAuthority, must include "ROLE_" in front of role name.
        System.out.println("Search user from DB...");
        AppUser user = Optional.ofNullable(userRepository.findUserByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> adminAuthority = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        List<GrantedAuthority> userAuthority = AuthorityUtils.createAuthorityList("ROLE_USER");

        System.out.println(user.getUsername());

        return new User(user.getUsername(), user.getPassword(), user.isAdmin() ? adminAuthority : userAuthority );
    }

}
