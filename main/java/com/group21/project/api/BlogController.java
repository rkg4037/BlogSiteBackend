package com.group21.project.api;

import com.group21.project.dao.BlogDAO;
import com.group21.project.model.Blog;
import com.group21.project.model.BlogHeader;
import com.group21.project.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("blog")
@RestController
public class BlogController {
    private final BlogService blogService ;

    @Autowired
    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping(path="/user")
    public List<BlogHeader> displayUserBlogs(@RequestParam String username){
        return blogService.displayUserBlogs(username) ;
    }

    @GetMapping(path="/recommended")
    public List<BlogHeader> recommendedBlogs(@RequestParam String category){
        return blogService.recommendedBlogs(category) ;
    }

    @PostMapping
    public void addBlog(@RequestBody Blog blog){
        //System.out.println(blog.getBlogHeader().getUsername()) ;
        blogService.addBlog(blog);
    }

    @PostMapping(path="/edit")
    public void editBlog(@RequestBody Blog blog){
        blogService.editBlog(blog);
    }

    @DeleteMapping
    public void deleteBlog(@RequestParam String username,@RequestParam UUID blogID){
        blogService.deleteBlog(username, blogID);
    }

    @GetMapping
    public Blog getBlog(@RequestParam UUID blogID){
        return blogService.getBlog(blogID) ;
    }

}
