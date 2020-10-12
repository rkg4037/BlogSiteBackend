package com.group21.blogsite.dao;

import com.group21.blogsite.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    void registerUser(User user) ;
    boolean validateUser(String username,String password) ;
}
