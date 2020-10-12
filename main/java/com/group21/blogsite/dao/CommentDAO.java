package com.group21.blogsite.dao;

import com.group21.blogsite.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentDAO {
    List<Comment> getComments(UUID blogID) ;
    void addComment(UUID blogID,Comment comment) ;
    void deleteComment(UUID blogID,UUID commentID) ;
    void editComment(UUID blogID,UUID commentID,Comment comment) ;
}
