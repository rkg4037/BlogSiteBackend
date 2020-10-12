package com.group21.blogsite.dao;

import com.group21.blogsite.model.Blog;
import com.group21.blogsite.model.BlogHeader;

import java.util.List;
import java.util.UUID;

public interface BlogDAO {
    List<BlogHeader> displayUserBlogs(String username) ;
    List<BlogHeader> recommendedBlogs(String username) ;
    void addBlog(Blog blog) ;
    void deleteBlog(UUID blogID) ;
    void editBlog(UUID blogID,Blog blog) ;
    Blog getBlog(UUID blogID) ;
}
