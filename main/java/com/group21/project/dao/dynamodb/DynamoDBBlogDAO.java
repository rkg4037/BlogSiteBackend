package com.group21.project.dao.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.KeyPair;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.group21.project.dao.BlogDAO;
import com.group21.project.dao.dynamodb.item.BlogItem;
import com.group21.project.dao.dynamodb.item.UserBlogMapping;
import com.group21.project.dao.dynamodb.mapper.BlogItemMapper;
import com.group21.project.model.Blog;
import com.group21.project.model.BlogHeader;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("dynamo-db-blog")
public class DynamoDBBlogDAO implements BlogDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBBlogDAO.class);

    //private BlogItemMapper blogItemMapper = Mappers.getMapper(BlogItemMapper.class) ;
    private BlogItemMapper blogItemMapper = new BlogItemMapper() ;
    @Autowired
    private DynamoDBMapper mapper ;

    @Override
    public List<BlogHeader> displayUserBlogs(String username) {
        String hashKey = "User#"+username ;

        /*
            Hashmap created to get all entries with PK value as ("User#" + usrename) and
            SK value which starts with "Blog#" to get all blogIDs corresponding to a user
         */
        HashMap<String, AttributeValue> eav = new HashMap<>() ;
        eav.put(":v1",new AttributeValue().withS(hashKey)) ;
        eav.put(":v2",new AttributeValue().withS("Blog#")) ;

        /*
            fetching all mappings of blogIDs for a user
         */
        DynamoDBQueryExpression<UserBlogMapping> queryExpression = new DynamoDBQueryExpression<UserBlogMapping>()
                .withKeyConditionExpression("PK=:v1 and begins_with(SK,:v2)")
                .withExpressionAttributeValues(eav) ;

        List<UserBlogMapping> userBlogMappingList = mapper.query(UserBlogMapping.class,queryExpression) ;

        if(userBlogMappingList.size()==0)
            return null ;

        /*
            List of KeyPairs made in which keypair, hash as well as range key corresponds to blogID to find Blog object.
            This is needed for batchLoad operation of dynamodb
         */
        List<KeyPair> keyPairs = new ArrayList<>() ;
        KeyPair keyPair ;
        for(UserBlogMapping item:userBlogMappingList){
            keyPair = new KeyPair() ;
            keyPair.withHashKey(item.getSK()) ;
            keyPair.withRangeKey(item.getSK()) ;
            keyPairs.add(keyPair) ;
        }

        /*
            fetching blogItems from table and adding it to arraylist
         */

        List<BlogHeader> blogHeaders = new ArrayList<>() ;
        Map<Class<?>,List<KeyPair>> map = new HashMap<>() ;
        map.put(BlogItem.class,keyPairs) ;
        Map<String,List<Object>> result = mapper.batchLoad(map) ;
        List<Object> objects = result.get("website") ;

        for(Object obj : objects){
            BlogItem blogItem = (BlogItem)obj ;
            blogHeaders.add(blogItem.getBlogHeader()) ;
        }

        return blogHeaders ;
    }

    @Override
    public List<BlogHeader> recommendedBlogs(String category) {

        HashMap<String, AttributeValue> eav = new HashMap<>() ;
        eav.put(":v1",new AttributeValue().withS("Blog#")) ;

        DynamoDBQueryExpression<BlogItem> queryExpression = new DynamoDBQueryExpression<BlogItem>()
                .withKeyConditionExpression("begins_with(PK,:v1) and begins_with(SK,:v1)")
                .withExpressionAttributeValues(eav) ;
        List<BlogItem> blogItems = mapper.query(BlogItem.class,queryExpression) ;
        List<BlogHeader> blogHeaders = new ArrayList<>() ;

        for(BlogItem item : blogItems){
            blogHeaders.add(item.getBlogHeader()) ;
        }
        // category wise
        /*HashMap<String,AttributeValue> eav = new HashMap<>() ;
        eav.put(":v1",new AttributeValue().withS(category)) ;

        DynamoDBQueryExpression<BlogItem> queryExpression = new DynamoDBQueryExpression<BlogItem>()
                .withIndexName("CategoryIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("category = :v1")
                .withExpressionAttributeValues(eav) ;

        List<BlogItem> blogItems = mapper.query(BlogItem.class,queryExpression) ;
        List<BlogHeader> blogHeaders = new ArrayList<>() ;
        for(BlogItem item : blogItems){
            blogHeaders.add(item.getBlogHeader()) ;
        }*/
        return blogHeaders;
    }

    @Override
    public void addBlog(Blog blog) {
        blog.setBlogID(UUID.randomUUID()) ;
        BlogHeader blogHeader = blog.getBlogHeader() ;
        blogHeader.setBlogID(blog.getBlogID());
        blog.setBlogHeader(blogHeader);
        BlogItem blogItem = blogItemMapper.from(blog) ;
        String username = blogHeader.getUsername() ;
        //System.out.println(blogItem.getPK()+" "+blogItem.getSK());
        UserBlogMapping userBlogMapping = new UserBlogMapping() ;
        userBlogMapping.setSK(blogItem.getPK());
        userBlogMapping.setPK(username) ;
        mapper.save(blogItem) ;
        mapper.save(userBlogMapping) ;
        return ;
    }

    @Override
    public void deleteBlog(String username,UUID blogID) {
        UserBlogMapping userBlogMapping = new UserBlogMapping() ;
        userBlogMapping.setPK(username) ;
        userBlogMapping.setSK(blogID) ;
        mapper.delete(userBlogMapping);

        String key = "Blog#"+blogID ;
        BlogItem blogItem = mapper.load(BlogItem.class,key,key) ;
        mapper.delete(blogItem);
    }

    @Override
    public void editBlog(Blog blog) {
        BlogItem blogItem = blogItemMapper.from(blog) ;
        mapper.save(blogItem) ;
        return ;
    }

    @Override
    public Blog getBlog(UUID blogID) {
        String key = "Blog#"+blogID ;
        BlogItem blogItem = mapper.load(BlogItem.class,key,key) ;
        Blog blog = blogItemMapper.to(blogItem) ;
        BlogHeader blogHeader = blogItem.getBlogHeader() ;
        blogHeader.setHits(blogHeader.getHits()+1);
        blogItem.setBlogHeader(blogHeader);
        mapper.save(blogItem);
        return blog ;
    }
}
