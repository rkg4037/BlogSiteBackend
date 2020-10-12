package com.group21.blogsite.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BlogHeader {
    private UUID blogID ;
    private String username ;
    private long creationTime ;
    private long lastUpdatedTime ;
    private String heading ;
    private String category ;
}
