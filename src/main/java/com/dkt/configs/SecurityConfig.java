package com.dkt.configs;


import com.dkt.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final CustomUserDetailService customUserDetailService;

    public SecurityConfig(CustomUserDetailService customUserDetailService){
        this.customUserDetailService = customUserDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/login")
                .permitAll()

                .antMatchers(HttpMethod.GET, "/users")
                .hasRole("ROOT")
                .antMatchers(HttpMethod.POST, "/users")
                .hasRole("ROOT")
                .antMatchers(HttpMethod.PUT, "/users/**")
                .hasRole("ROOT")
                .antMatchers(HttpMethod.DELETE, "/users/**")
                .hasRole("ROOT")

                .antMatchers(HttpMethod.GET, "/bizweb-stores/**")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/bizweb-stores/**")
                .denyAll()
                .antMatchers(HttpMethod.PUT, "/bizweb-stores/**")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/bizweb-stores/**")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/bizweb-stores/**/**/**")
                .hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/pages/**")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/pages")
                .denyAll()
                .antMatchers(HttpMethod.PUT, "/pages/**")
                .denyAll()
                .antMatchers(HttpMethod.DELETE, "/pages/**")
                .denyAll()

                .antMatchers(HttpMethod.GET, "/accounts/**")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/accounts")
                .denyAll()
                .antMatchers(HttpMethod.PUT, "/accounts/**")
                .denyAll()
                .antMatchers(HttpMethod.DELETE, "/accounts/**")
                .denyAll()

                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
    }

    // encode password before attempt authentication (searching for db)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
