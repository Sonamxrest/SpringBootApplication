package com.xrest.spring.Controller;

import com.xrest.spring.Service.BaseService;
import com.xrest.spring.Service.UserService;
import com.xrest.spring.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController<User,Long> {
    private final UserService userService;
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("")
    public String hello(){
        return "index.html";
    }
}
