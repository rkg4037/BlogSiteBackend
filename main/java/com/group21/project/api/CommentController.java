package com.group21.project.api;

import com.group21.project.dao.CommentDAO;
import com.group21.project.model.Comment;
import com.group21.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("comment")
@RestController
public class CommentController {
    private final CommentService commentService ;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments(@RequestParam UUID blogID){
        return commentService.getComments(blogID) ;
    }

    @PostMapping
    public void addComment(@RequestParam UUID blogID,@RequestBody Comment comment){
        commentService.addComment(blogID,comment);
        return ;
    }

    @DeleteMapping
    public void deleteComment(@RequestParam UUID blogID, @RequestParam UUID commentID){
        commentService.deleteComment(blogID, commentID);
        return ;
    }

    @PostMapping(path="/edit")
    public void editComment(@RequestBody Comment comment){
        commentService.editComment(comment);
        return ;
    }

}
