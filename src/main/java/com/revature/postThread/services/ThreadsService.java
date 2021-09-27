package com.revature.postThread.services;

import com.revature.postThread.models.Threads;
import com.revature.postThread.repositories.ThreadsRepo;

public class ThreadsService {

    private final ThreadsRepo threadsRepo = new ThreadsRepo();

    /**
     * @param threads - the thread being sent to the repo for persistence to database
     * @throws Exception
     *
     * @authors - Charles Mettee
     */
    public void addThreads(Threads threads) throws Exception {
        if(!isValid(threads)){
            throw new Exception("An error occurred");
        }

        threadsRepo.addThreads(threads);
    }

    /**
     * @param threads - the thread being validated
     * @return - boolean value indicating whether or not the thread is valid
     *
     * @authors - Charles Mettee
     */
    public boolean isValid(Threads threads){
        if(threads.getSubject() == null || threads.getSubject().trim().equals("")){
            return false;
        }
        return true;
    }


}
