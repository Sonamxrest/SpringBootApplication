package com.xrest.spring.Service;

import com.xrest.spring.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<User,Long>, UserDetailsService {
}
