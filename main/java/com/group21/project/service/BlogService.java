package com.group21.project.service;

import com.group21.project.dao.BlogDAO;
import com.group21.project.model.Blog;
import com.group21.project.model.BlogHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    private final BlogDAO blogDAO ;

    @Autowired
    public BlogService(@Qualifier("dynamo-db-blog") BlogDAO blogDAO){
        this.blogDAO = blogDAO ;
    }

    public List<BlogHeader> displayUserBlogs(String username){
        return blogDAO.displayUserBlogs(username) ;
    }

    public List<BlogHeader> recommendedBlogs(String category){
        return blogDAO.recommendedBlogs(category) ;
    }

    public void addBlog(Blog blog){
        blogDAO.addBlog(blog);
    }

    public void editBlog(Blog blog){
        blogDAO.editBlog(blog);
    }

    public void deleteBlog(String username,UUID blogID){
        blogDAO.deleteBlog(username, blogID);
    }

    public Blog getBlog(UUID blogID){
        return blogDAO.getBlog(blogID) ;
    }
}
