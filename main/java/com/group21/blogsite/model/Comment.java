package com.group21.blogsite.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private UUID commentID ;
    private String username ;
    private String body ;
    private long creationTime ;
}
