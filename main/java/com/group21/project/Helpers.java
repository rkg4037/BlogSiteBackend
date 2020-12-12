package com.group21.project;

import com.group21.project.model.BlogHeader;

public class Helpers {
    public static float getLikeDislikeRatio(BlogHeader blogHeader){
        long likes = blogHeader.getLikes() ;
        if(likes==0)
            likes=1 ;
        long dislikes = blogHeader.getDislikes() ;
        if(dislikes==0)
            dislikes=1 ;
        long hits = blogHeader.getHits() ;

        return likes/(float)dislikes * hits ;
    }
}
