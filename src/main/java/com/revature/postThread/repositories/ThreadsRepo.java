package com.revature.postThread.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.revature.postThread.models.Threads;

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
        Threads queryItem = Threads.builder().id(id).build();

        return dbReader.query(Threads.class,new DynamoDBQueryExpression<Threads>().withHashKeyValues(queryItem)).get(0);
    }
    public void updateChild_count(Threads subforum) {
        dbReader.save(subforum);
    }

}
