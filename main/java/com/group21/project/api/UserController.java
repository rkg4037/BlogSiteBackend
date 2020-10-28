package com.group21.project.api;

import com.group21.project.dao.UserDAO;
import com.group21.project.model.User;
import com.group21.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public void registerUser(@RequestBody User user){
        //System.out.println(user.getEmailID()) ;
        userService.registerUser(user);
        return ;
    }

    @GetMapping
    public boolean validateUser(@RequestParam String username, @RequestParam String password){
        return userService.validateUser(username,password) ;
    }
}
