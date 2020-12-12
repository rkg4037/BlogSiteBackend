package com.group21.project.service;

import com.group21.project.dao.UserDAO;
import com.group21.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("dynamo-db-user") UserDAO userDAO){
        this.userDAO = userDAO ;
    }

    public void registerUser(User user){
        userDAO.registerUser(user) ;
    }

    public boolean validateUser(@RequestParam String username, @RequestParam String password){
        return userDAO.validateUser(username,password) ;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers() ;
    }
}
