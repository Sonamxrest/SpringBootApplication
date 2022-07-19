package com.xrest.spring.ServiceImpl;
import com.xrest.spring.Service.UserService;
import com.xrest.spring.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService {
}
