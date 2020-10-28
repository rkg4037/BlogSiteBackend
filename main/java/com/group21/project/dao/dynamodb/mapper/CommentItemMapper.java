package com.group21.project.dao.dynamodb.mapper;

import com.group21.project.dao.dynamodb.item.CommentItem;
import com.group21.project.model.Comment;
import java.util.UUID;

public class CommentItemMapper{
    public CommentItem from(Comment comment){
        String key = "Comment#"+comment.getCommentID() ;
        CommentItem commentItem = new CommentItem(key,key,comment.getUsername(),comment.getBody(),comment.getCreationTime()) ;
        return commentItem ;
    }

    public Comment to(CommentItem commentItem){
        UUID commentID = UUID.fromString(commentItem.getPK().substring(commentItem.getPK().indexOf('#')+1));
        Comment comment  = new Comment(commentID,commentItem.getUsername(),commentItem.getBody(),commentItem.getCreationTime()) ;
        return comment ;
    }
}
