package com.xrest.spring.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xrest.spring.Repository.UserRepo;
import com.xrest.spring.models.User;
import liquibase.util.StringUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepo repo;
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserRepo repo) {
        super(authenticationManager);
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTProperties.HEADER_TYPE);
        if(null == header || !header.contains(JWTProperties.TOKEN_TYPE)) {
            chain.doFilter(request,response);
            return;
        }
        Authentication authentication = getUsernameAndPasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

    private Authentication getUsernameAndPasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWTProperties.HEADER_TYPE);
        if (token != null) {
            try {
                //getting username from token where we stored our username
            String username = JWT.require(Algorithm.HMAC512(JWTProperties.SECRET_KEY))
                    .build().
                    verify(token.replace(JWTProperties.TOKEN_TYPE,""))
                    .getSubject();
            if (!StringUtil.isEmpty(username)) {
                User user = repo.findByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                return authenticationToken;
            }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }
}
