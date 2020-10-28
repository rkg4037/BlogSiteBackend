package com.group21.project.dao.dynamodb.item;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "website")
public class UserItem {

    private String PK ;

    private String SK ;

    private String emailID ;

    private String password ;

    public UserItem(){}

    public UserItem(String PK, String SK, String emailID, String password) {
        this.PK = PK;
        this.SK = SK;
        this.emailID = emailID;
        this.password = password;
    }

    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return PK;
    }

    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return SK;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "EmailIndex",attributeName = "emailID")
    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }
    
}
