package com.xrest.spring.ServiceImpl;
import com.xrest.spring.Repository.UserRepo;
import com.xrest.spring.Service.UserService;
import com.xrest.spring.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class  UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService {
    private UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username);
    }
}
