package com.group21.project.dao.dynamodb.item;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.UUID;

@DynamoDBTable(tableName = "website")
public class CommentItem {

    private String PK ;

    private String SK ;

    private String username ;

    private String body ;

    private long creationTime ;

    public CommentItem(){}

    public CommentItem(String PK, String SK, String username, String body, long creationTime) {
        this.PK = PK;
        this.SK = SK;
        this.username = username;
        this.body = body;
        this.creationTime = creationTime;
    }

    @DynamoDBHashKey(attributeName = "PK")
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

    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @DynamoDBAttribute(attributeName = "creationTime")
    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    void setPK(UUID commentID){
        this.PK = "Comment#"+commentID ;
    }

    void setSK(UUID commentID){
        this.SK = "Comment#"+commentID ;
    }


}
