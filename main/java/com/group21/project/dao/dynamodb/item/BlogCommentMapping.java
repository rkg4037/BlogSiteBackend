package com.group21.project.dao.dynamodb.item;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.UUID;

@DynamoDBTable(tableName = "website")
public class BlogCommentMapping {

    private String PK ;

    private String SK ;

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

    public void setPK(UUID blogID){
        this.PK = "Blog#"+blogID ;
    }

    public void setSK(UUID commentID){
        this.SK = "Comment#"+commentID ;
    }

}
