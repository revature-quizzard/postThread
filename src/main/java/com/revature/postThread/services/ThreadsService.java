package com.revature.postThread.services;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.revature.postThread.models.Threads;
import com.revature.postThread.repositories.ThreadsRepo;

public class ThreadsService {

    private final ThreadsRepo threadsRepo;
    private final LambdaLogger lambdaLogger;

    public ThreadsService(ThreadsRepo threadsRepo, LambdaLogger lambdaLogger){
        this.threadsRepo = threadsRepo;
        this.lambdaLogger = lambdaLogger;
    }

    /**
     * @param threads - the thread being sent to the repo for persistence to database
     * @throws Exception
     *
     * @author - Charles Mettee
     */
    public void addThreads(Threads threads) throws Exception {
        if(!isValid(threads)){
            throw new Exception("An error occurred");
        }
        Threads subforum = threadsRepo.getSubforum(threads.getParent());
        subforum.setChild_count(subforum.getChild_count() + 1);
        threadsRepo.updateChild_count(subforum);

        lambdaLogger.log("The provided thread is valid; Update successful.");
        threadsRepo.addThreads(threads);
    }

    /**
     * @param threads - the thread being validated
     * @return - boolean value indicating whether or not the thread is valid
     *
     * @author - Charles Mettee
     */
    public boolean isValid(Threads threads){
        if(threads == null){
            lambdaLogger.log("Add thread failed because the given thread was null.");
            return false;
        }
        if(threads.getSubject() == null || threads.getSubject().trim().equals("")){
            lambdaLogger.log("Add thread failed because the given thread did not contain a title.");
            return false;
        }

        if(threads.getAncestors() == null || threads.getAncestors().size() != 1) {
            lambdaLogger.log("Add thread failed because the given thread did not have the specified number of ancestors.");
            return false;
        }
        if(threads.getParent() == null || threads.getParent().trim().equals("")){
            lambdaLogger.log("Add thread failed because the given thread did not belong to a subforum.");
            return false;
        }
        if(threads.getDescription() == null || threads.getDescription().trim().equals("")){
            lambdaLogger.log("Add thread failed because the given thread's original post did not contain a body.");
            return false;
        }
        if(threads.getOwner() == null || threads.getOwner().trim().equals("")){
            lambdaLogger.log("Add thread failed because the given thread did not have an owner.");
            return false;
        }
        return true;
    }


}
