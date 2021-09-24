package com.revature.postThread;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@DynamoDBTable(tableName = "ForumNodes")
public class Threads {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String subject;

    @DynamoDBAttribute
    private List<String> ancestors;

    @DynamoDBAttribute
    private String parent;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private int childCount;

    @DynamoDBAttribute
    private LocalDateTime dateCreated;

    @DynamoDBAttribute
    private String owner;

    @DynamoDBAttribute
    private List<String> tags;

}
