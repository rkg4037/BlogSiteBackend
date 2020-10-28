package com.group21.project.dao;


import com.group21.project.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentDAO {
    List<Comment> getComments(UUID blogID) ;
    void addComment(UUID blogID,Comment comment) ;
    void deleteComment(UUID blogID,UUID commentID) ;
    void editComment(Comment comment) ;
}
