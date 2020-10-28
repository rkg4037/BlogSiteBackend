package com.group21.project.dao.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.KeyPair;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.group21.project.dao.CommentDAO;
import com.group21.project.dao.dynamodb.item.BlogCommentMapping;
import com.group21.project.dao.dynamodb.item.CommentItem;
import com.group21.project.dao.dynamodb.mapper.CommentItemMapper;
import com.group21.project.model.Comment;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("dynamo-db-comment")
public class DynamoDBCommentDAO implements CommentDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBCommentDAO.class);

    //private CommentItemMapper commentItemMapper = Mappers.getMapper(CommentItemMapper.class) ;
    private CommentItemMapper commentItemMapper = new CommentItemMapper() ;
    @Autowired
    private DynamoDBMapper mapper ;

    /*
        returns the list of comments on a blog/article
     */
    @Override
    public List<Comment> getComments(UUID blogID) {

        String hashKey = "Blog#"+blogID ;

        /*
            Hashmap created to get all entries with PK value as ("Blog#" + blogID) and
            SK value which starts with "Comment#" to get all commentIDs corresponding to a blog
         */
        HashMap<String, AttributeValue> eav = new HashMap<>() ;
        eav.put(":v1",new AttributeValue().withS(hashKey)) ;
        eav.put(":v2",new AttributeValue().withS("Comment#")) ;

        /*
            fetching all mappings of blogID to different commentIDs
         */
        DynamoDBQueryExpression<BlogCommentMapping> queryExpression = new DynamoDBQueryExpression<BlogCommentMapping>()
                .withKeyConditionExpression(" PK=:v1 and begins_with(SK,:v2)")
                .withExpressionAttributeValues(eav);
        List<BlogCommentMapping> blogCommentMappingList = mapper.query(BlogCommentMapping.class,queryExpression) ;

        /*
            List of KeyPairs made in which keypair, hash as well as range key corresponds to commentID to find Comment object.
            This is needed for batchLoad operation of dynamodb
         */
        List<KeyPair> keyPairs = new ArrayList<>() ;
        KeyPair keyPair ;
        for(BlogCommentMapping item: blogCommentMappingList){
            keyPair = new KeyPair() ;
            keyPair.withHashKey(item.getSK()) ;
            keyPair.withRangeKey(item.getSK()) ;
            keyPairs.add(keyPair) ;
        }

        /*
            fetching commentItems from table and adding it in arraylist
         */
        Map<Class<?>,List<KeyPair>> map  = new HashMap<>() ;
        map.put(CommentItem.class,keyPairs) ;
        Map<String,List<Object>> result = mapper.batchLoad(map) ;
        List<Object> objects = result.get("website") ;
        List<CommentItem> commentItems = new ArrayList<>() ;
        for(Object obj:objects){
            commentItems.add((CommentItem)obj) ;
        }

        /*
        converting commentItem to comment
         */
        List<Comment> comments = new ArrayList<>() ;
        for(CommentItem commentItem: commentItems){
            comments.add(commentItemMapper.to(commentItem)) ;
        }

        return comments ;
    }

    @Override
    public void addComment(UUID blogID, Comment comment) {
        comment.setCommentID(UUID.randomUUID()) ;
        CommentItem commentItem = commentItemMapper.from(comment) ;

        BlogCommentMapping blogCommentMapping = new BlogCommentMapping();
        blogCommentMapping.setPK(blogID) ;
        blogCommentMapping.setSK(comment.getCommentID());

        /*
            saves mapping blogID and commentID
         */
        mapper.save(blogCommentMapping) ;
        /*
            saves comment
         */
        mapper.save(commentItem) ;

        return ;
    }

    @Override
    public void deleteComment(UUID blogID, UUID commentID) {
        BlogCommentMapping blogCommentMapping = new BlogCommentMapping() ;
        blogCommentMapping.setSK(commentID);
        blogCommentMapping.setPK(blogID);
        mapper.delete(blogCommentMapping);
        String key = "Comment#"+commentID ;
        CommentItem commentItem = mapper.load(CommentItem.class,key,key) ;
        mapper.delete(commentItem);
        return ;
    }

    @Override
    public void editComment(Comment comment) {
        CommentItem commentItem = commentItemMapper.from(comment) ;
        mapper.save(commentItem) ;
        return ;
    }
}
