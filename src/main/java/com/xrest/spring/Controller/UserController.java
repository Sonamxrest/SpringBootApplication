package com.xrest.spring.Controller;

import com.xrest.spring.Service.UserService;
import com.xrest.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("v1/user")
public class UserController extends BaseController<User,Long> {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserService userService, @Autowired PasswordEncoder passwordEncoder) {
        super(userService);
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
    }
//    @GetMapping("")
//    private String hello(){
//        return "index.html";
//    }
}
