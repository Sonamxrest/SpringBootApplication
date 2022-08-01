package com.xrest.spring.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrest.spring.Repository.UserRepo;
import com.xrest.spring.Service.UserService;
import com.xrest.spring.core.JWT.JwtAuthenticationFilter;
import com.xrest.spring.core.JWT.JwtAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    private ObjectMapper objectMapper;

    public  final UserRepo userRepo;

    public WebSecurityConfig(UserService userService, ObjectMapper objectMapper, UserRepo userRepo) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        try{
            auth.authenticationProvider(authenticationProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable().addFilter(new JwtAuthenticationFilter(objectMapper, authenticationManager()))
                .addFilter(new JwtAuthorization(authenticationManager(),userRepo)).
                authorizeRequests()
                .antMatchers("/login")
                .permitAll().antMatchers("/images/**").permitAll().antMatchers("/v1/user/**").authenticated();
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
