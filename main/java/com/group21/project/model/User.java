package com.group21.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty
    String username ;

    @JsonProperty
    String emailID ;

    @JsonProperty
    String password ;

    public User(@JsonProperty("username") String username, @JsonProperty("emailID") String emailID, @JsonProperty("password") String password) {
        this.username = username;
        this.emailID = emailID;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
