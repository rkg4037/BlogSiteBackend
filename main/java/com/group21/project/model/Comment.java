package com.group21.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Comment {
    private UUID commentID ;
    private String username ;
    private String body ;
    private long creationTime ;

    public Comment(@JsonProperty("commentID") UUID commentID, @JsonProperty("username") String username, @JsonProperty("body") String body, @JsonProperty("creationTime") long creationTime) {
        this.commentID = commentID;
        this.username = username;
        this.body = body;
        this.creationTime = creationTime;
    }

    public UUID getCommentID() {
        return commentID;
    }

    public void setCommentID(UUID commentID) {
        this.commentID = commentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}