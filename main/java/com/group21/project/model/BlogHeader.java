package com.group21.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class BlogHeader {
    private UUID blogID ;
    private String username ;
    private long creationTime ;
    private long lastUpdatedTime ;
    private long hits ;
    private long likes ;
    private long dislikes ;
    private String heading ;
    private String category ;

    public BlogHeader(@JsonProperty("blogID") UUID blogID, @JsonProperty("username") String username, @JsonProperty("creationTime") long creationTime, @JsonProperty("lastUpdatedTime") long lastUpdatedTime, @JsonProperty("hits") long hits, @JsonProperty("likes") long likes, @JsonProperty("dislikes") long dislikes, @JsonProperty("heading") String heading, @JsonProperty("category") String category) {
        this.blogID = blogID;
        this.username = username;
        this.creationTime = creationTime;
        this.lastUpdatedTime = lastUpdatedTime;
        this.hits = hits ;
        this.likes=likes ;
        this.dislikes=dislikes ;
        this.heading = heading;
        this.category = category;
    }

    public UUID getBlogID() {
        return blogID;
    }

    public void setBlogID(UUID blogID) {
        this.blogID = blogID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }
}
