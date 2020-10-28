package com.group21.project.dao;

import com.group21.project.model.User;

public interface UserDAO {
    void registerUser(User user) ;
    boolean validateUser(String username,String password) ;
}


