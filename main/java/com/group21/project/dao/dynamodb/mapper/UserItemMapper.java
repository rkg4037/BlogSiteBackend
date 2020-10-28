package com.group21.project.dao.dynamodb.mapper;

import com.group21.project.dao.dynamodb.item.UserItem;
import com.group21.project.model.User;

public class UserItemMapper{
    public UserItem from(User user){
        String key = "User#"+user.getUsername() ;
        UserItem userItem = new UserItem(key,key,user.getEmailID(),user.getPassword()) ;
        return userItem ;
    }

    public User to(UserItem userItem){
        String username = userItem.getPK().substring(userItem.getPK().indexOf('#')+1);
        User user = new User(username,userItem.getEmailID(),userItem.getPassword()) ;
        return user ;
    }
}
