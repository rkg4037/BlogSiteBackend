package com.group21.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.text.html.HTMLDocument;

public class BlogContent {
    @JsonProperty
    private String html ;

    public BlogContent() {
    }

    public BlogContent(String htmlDocument) {
        this.html = htmlDocument;
    }

    public String getHtml() {
        return html;
    }

    public void setHtmlDocument(String htmlDocument) {
        this.html = htmlDocument;
    }
}
