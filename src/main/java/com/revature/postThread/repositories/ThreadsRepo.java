package com.revature.postThread.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.revature.postThread.models.Threads;

import java.util.Arrays;

public class ThreadsRepo {

    private final DynamoDBMapper dbReader;

    /**
     * Constructor for initializing dbReader;
     * @author - Charles Mettee
     */
    public ThreadsRepo() {
        dbReader = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
    }


    /**
     * @param threads - the Threads object being posted to the database
     * @author - Charles Mettee
     */
    public void addThreads(Threads threads){
        dbReader.save(threads);
    }

    /**
     * @param id - current Thread's id
     * @author - Sean Smith
     */
    public Threads getSubforum(String id) {
        Threads queryItem = new Threads();
        queryItem.setId(id);

        return dbReader.query(Threads.class,new DynamoDBQueryExpression<Threads>().withHashKeyValues(queryItem)).get(0);
    }

    /**
     * @param subforum - the subforum being updated
     * @author - Sean Smith
     */
    public void updateChild_count(Threads subforum) {
        dbReader.save(subforum);
    }

}
