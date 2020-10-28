package com.group21.project.dao.dynamodb.mapper;

import com.group21.project.dao.dynamodb.item.BlogItem;
import com.group21.project.model.Blog;
import java.util.UUID;

public class BlogItemMapper{
    public BlogItem from(Blog blog){
        String key = "Blog#"+blog.getBlogID() ;
        BlogItem blogItem = new BlogItem(key,key,blog.getBlogHeader(),blog.getBody(),blog.getCategory()) ;
        return blogItem ;
    }

    public Blog to(BlogItem blogItem){
        UUID blogID = UUID.fromString(blogItem.getPK().substring(blogItem.getPK().indexOf('#')+1));
        Blog blog = new Blog(blogID,blogItem.getBlogHeader(),blogItem.getBody(),blogItem.getCategory()) ;
        return blog ;
    }
}
