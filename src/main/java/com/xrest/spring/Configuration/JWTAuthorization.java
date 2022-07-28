package com.xrest.spring.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xrest.spring.Repository.UserRepo;
import com.xrest.spring.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorization extends BasicAuthenticationFilter {
    private final UserRepo userRepo;
    public JWTAuthorization(AuthenticationManager authenticationManager, UserRepo userRepo) {
        super(authenticationManager);
        this.userRepo = userRepo;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTConstant.HEADER_TYPE);
        if (header == null || !header.contains(JWTConstant.PREFIX_TYPE)) {
            chain.doFilter(request,response);
            return;
        }
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {

    String token = request.getHeader(JWTConstant.HEADER_TYPE);
    if (token != null) {
        String username = JWT.require(Algorithm.HMAC512(JWTConstant.SECRET_KEY.getBytes())).build().verify(token.split(" ")[1]).getSubject();
        User user = userRepo.findByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
        return usernamePasswordAuthenticationToken;
    }
    return null;
    }
}
