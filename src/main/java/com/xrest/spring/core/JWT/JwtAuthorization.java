package com.xrest.spring.core.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xrest.spring.Repository.UserRepo;
import com.xrest.spring.Service.UserService;
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
import java.util.Arrays;
import java.util.Base64;

public class JwtAuthorization extends BasicAuthenticationFilter {
    private final UserRepo userService;
    public JwtAuthorization(AuthenticationManager authenticationManager, UserRepo userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtConstant.HEADER);
        if (token == null || !token.contains(JwtConstant.PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = usernamePasswordAuth(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
    }

    private Authentication usernamePasswordAuth(HttpServletRequest request) {
        String token = request.getHeader(JwtConstant.HEADER).split(" ")[1];
        if (token != null) {
//        String username = Arrays.toString(Base64.getDecoder().decode(token));
//            byte[] decodedBytes = Base64.getDecoder().decode(token);
//            String decodedString = new String(decodedBytes);
            String decodedString = JWT
                    .require(Algorithm.HMAC512(JwtConstant.KEY.getBytes()))
                    .build().
                    verify(token).getSubject();
            User u = userService.findByUsername(decodedString);
            return new UsernamePasswordAuthenticationToken(u.getUsername(),null,u.getAuthorities());

        }
        return  null;
    }
}
