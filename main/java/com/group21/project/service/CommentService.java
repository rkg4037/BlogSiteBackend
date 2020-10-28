package com.group21.project.service;

import com.group21.project.dao.CommentDAO;
import com.group21.project.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentDAO commentDAO ;

    @Autowired
    public CommentService(@Qualifier("dynamo-db-comment") CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getComments(UUID blogID){
        return commentDAO.getComments(blogID) ;
    }

    public void addComment(UUID blogID,Comment comment){
        commentDAO.addComment(blogID,comment);
        return ;
    }

    public void deleteComment(UUID blogID, UUID commentID){
        commentDAO.deleteComment(blogID, commentID);
        return ;
    }

    public void editComment(Comment comment){
        commentDAO.editComment(comment);
        return ;
    }

}
