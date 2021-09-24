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
    private int child_count;

    @DynamoDBAttribute
    private LocalDateTime date_created;

    @DynamoDBAttribute
    private String owner;

    @DynamoDBAttribute
    private List<String> tags;

    /**
     * @param subject - Thread title
     * @param ancestors - List of ancestor node IDs of the thread
     * @param parent - Subforum id
     * @param description - The content of the original post in a thread
     * @param owner - Thread creator
     * @param tags - Thread subjects
     *
     * @authors - Charles Mettee, Sean Smith
     */
    public Threads(String subject, List<String> ancestors, String parent, String description, String owner, List<String> tags) {
        this.subject = subject;
        this.ancestors = ancestors;
        this.parent = parent;
        this.description = description;
        this.owner = owner;
        this.tags = tags;
        this.child_count = 0;
        this.date_created = LocalDateTime.now();
    }
}
