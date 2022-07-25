package com.xrest.spring.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //used to authenticate user using in memory authentication or jdbc connection
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and().
                withUser("user").password(passwordEncoder().encode("user")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http basic authorization
        http.
                authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
