package com.xrest.spring.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrest.spring.models.User;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthentication extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private AuthenticationManager authentication;

    public JWTAuthentication(ObjectMapper objectMapper, AuthenticationManager authentication) {
        this.objectMapper = objectMapper;
        this.authentication = authentication;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;
        try {
            user = objectMapper.readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
            return authentication.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
       User user = (User) authResult.getPrincipal();
        String token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JWTConstant.SECRET_KEY.getBytes()));
        response.getWriter().write( JWTConstant.PREFIX_TYPE +" " + token);
        response.getWriter().flush();


    }
}
