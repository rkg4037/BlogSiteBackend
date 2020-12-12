package com.group21.project.dao;

import com.group21.project.model.Blog;
import com.group21.project.model.BlogHeader;

import java.util.List;
import java.util.UUID;

public interface BlogDAO {
    List<BlogHeader> displayUserBlogs(String username) ;
    List<BlogHeader> recommendedBlogs(String category) ;
    void addBlog(Blog blog) ;
    void deleteBlog(String username,UUID blogID) ;
    void editBlog(Blog blog) ;
    void likeBlog(UUID blogID,int inc) ;
    void dislikeBlog(UUID blogID,int inc) ;
    Blog getBlog(UUID blogID) ;
}