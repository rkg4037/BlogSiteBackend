package com.group21.project.dao.dynamodb.item;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.group21.project.model.BlogHeader;
import java.util.UUID;

@DynamoDBTable(tableName = "website")
public class BlogItem {

    private String PK ;

    private String SK ;

    private BlogHeader blogHeader ;

    private String body ;

    private String category ;

    public BlogItem(){}

    public BlogItem(String PK, String SK, BlogHeader blogHeader, String body,String category) {
        this.PK = PK;
        this.SK = SK;
        this.blogHeader = blogHeader;
        this.body = body;
        this.category = category ;
    }

    @DynamoDBHashKey(attributeName = "PK")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "CategoryIndex",attributeName = "PK")
    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return SK;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }

    @DynamoDBAttribute(attributeName = "blogHeader")
    @DynamoDBTypeConvertedJson
    public BlogHeader getBlogHeader() {
        return blogHeader;
    }

    public void setBlogHeader(BlogHeader blogHeader) {
        this.blogHeader = blogHeader;
    }

    @DynamoDBAttribute(attributeName = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "CategoryIndex",attributeName = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    void setPK(UUID blogID){
        this.PK = "Blog#"+blogID ;
    }

    void setSK(UUID blogID){
        this.SK = "Blog#"+blogID ;
    }


}
