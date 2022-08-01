package com.xrest.spring.core.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrest.spring.models.User;
import liquibase.pro.packaged.D;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private AuthenticationManager authentication;

    public JwtAuthenticationFilter(ObjectMapper objectMapper, AuthenticationManager authentication) {
        this.objectMapper = objectMapper;
        this.authentication = authentication;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user =   (User) objectMapper.readValue(request.getInputStream(), User.class);
            if (user != null) {
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(), new ArrayList<>());
                return authentication.authenticate(token);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
     //genereate token after user is verified from database
        User user = (User) authResult.getPrincipal();
        //token can be custom
//        String token = Base64.getEncoder().encodeToString(user.getUsername().getBytes());
        String token = JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION))
                .withSubject(user.getUsername())
                .sign(Algorithm.HMAC512(JwtConstant.KEY.getBytes()));
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
