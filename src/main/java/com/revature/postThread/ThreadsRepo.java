package com.revature.postThread;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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



}
