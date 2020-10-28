package com.group21.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Blog {
    @JsonProperty
    private UUID blogID ;
    @JsonProperty
    private BlogHeader blogHeader ;
    @JsonProperty
    private String body ;
    @JsonProperty
    private String category ;

    public Blog(@JsonProperty("blogID") UUID blogID, @JsonProperty("blogHeader") BlogHeader blogHeader, @JsonProperty("body") String body,@JsonProperty("category") String category) {
        this.blogID = blogID;
        this.blogHeader = blogHeader;
        this.body = body;
        this.category = category ;
    }

    public UUID getBlogID() {
        return blogID;
    }

    public void setBlogID(UUID blogID) {
        this.blogID = blogID;
    }

    public BlogHeader getBlogHeader() {
        return blogHeader;
    }

    public void setBlogHeader(BlogHeader blogHeader) {
        this.blogHeader = blogHeader;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}