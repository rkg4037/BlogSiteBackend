package com.group21.blogsite.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Setter
public class User {
    String username ;
    String emailID ;
    String password ;
}
