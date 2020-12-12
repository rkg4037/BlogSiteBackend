package com.group21.project.dao.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.group21.project.dao.UserDAO;
import com.group21.project.dao.dynamodb.item.UserItem;
import com.group21.project.dao.dynamodb.mapper.UserItemMapper;
import com.group21.project.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository("dynamo-db-user")
public class DynamoDBUserDAO implements UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBUserDAO.class);

    private UserItemMapper userItemMapper = new UserItemMapper() ;
    @Autowired
    private DynamoDBMapper mapper ;
    @Override
    public void registerUser(User user) {
        UserItem userItem = userItemMapper.from(user) ;
        String username = userItem.getPK() ;
        //System.out.println(username) ;
        UserItem userItem1 = mapper.load(UserItem.class,userItem.getPK(),userItem.getSK()) ;
        //System.out.println("OK");
        if(userItem1!=null){
            //username exists
            return ;
        }

        String emailID = user.getEmailID() ;
        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue().withS(emailID)) ;

        DynamoDBQueryExpression<UserItem> queryExpression ;
        queryExpression = new DynamoDBQueryExpression<UserItem>()
                .withIndexName("EmailIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("emailID = :v1")
                .withExpressionAttributeValues(eav);

        List<UserItem> userItems = mapper.query(UserItem.class,queryExpression) ;

        if(userItems.size()==0)
            mapper.save(userItem) ;
        else{
            //throw exception username/email already exists
        }

        return  ;
    }

    @Override
    public boolean validateUser(String username, String password) {
        String key = "User#"+username ;
        UserItem userItem = mapper.load(UserItem.class,key,key) ;
        if(userItem.getPassword().equals(password))
            return true ;
        return false ;
    }

    @Override
    public List<User> getAllUsers() {
        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue().withS("User#")) ;

        List<User> users = new ArrayList<>() ;
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("begins_with(PK,:v1) and begins_with(SK,:v1)")
                .withExpressionAttributeValues(eav);

        //DynamoDBQueryExpression<UserItem> queryExpression ;
        //queryExpression = new DynamoDBQueryExpression<UserItem>() ;
                //.withKeyConditionExpression("begins_with(PK,:v1) and begins_with(SK,:v1)")
                //.withExpressionAttributeValues(eav);

        List<UserItem> userItems = mapper.scan(UserItem.class,scanExpression) ;

        for(UserItem userItem : userItems){
            users.add(userItemMapper.to(userItem)) ;
            System.out.print(userItem.getPK()) ;
        }

        return users ;
    }
}
