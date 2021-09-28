package com.revature.postThread;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.postThread.models.Threads;
import com.revature.postThread.repositories.ThreadsRepo;
import com.revature.postThread.services.ThreadsService;

import java.time.LocalDateTime;

public class PostThreadsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson mapper = new GsonBuilder().setPrettyPrinting().create();
    private final ThreadsRepo threadsRepo = new ThreadsRepo();
    private final ThreadsService threadsService = new ThreadsService(threadsRepo);

    /**
     * @param requestEvent
     * @param context
     * @return
     * @Author - Sean Smith, Charles Mettee
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("RECEIVED EVENT: " + requestEvent);

        Threads threads = mapper.fromJson(requestEvent.getBody(), Threads.class);
        threads.setDate_created(LocalDateTime.now().toString());

        APIGatewayProxyResponseEvent respEvent = new APIGatewayProxyResponseEvent();

        try{
            threadsService.addThreads(threads);
            respEvent.setStatusCode(201);
            return respEvent;
        } catch (Exception e){
            respEvent.setStatusCode(400);
            return respEvent;
        }

    }

}
