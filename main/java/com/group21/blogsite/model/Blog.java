package com.group21.blogsite.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Blog {
    private UUID blogID ;
    private BlogHeader blogHeader ;
    private String body ;
}
