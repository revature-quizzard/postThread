package com.revature.postThread.services;
import com.revature.postThread.models.Threads;

import com.revature.postThread.repositories.ThreadsRepo;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import java.util.Arrays;


public class ThreadsServiceTestSuite {

    ThreadsService sut;
    ThreadsRepo mockThreadsRepo;

    @BeforeEach
    void setUp(){
        mockThreadsRepo = mock(ThreadsRepo.class);
        sut = new ThreadsService(mockThreadsRepo);
    }

    @AfterEach
    void tearDown(){
        sut = null;
    }

    @Test
    public void isValid_returnsFalse_givenNullThread(){
        Threads thread = null;

        boolean testResult = sut.isValid(thread);

        assertFalse(testResult);
    }

    @Test
    public void isValid_returnsFalse_givenNullOrEmptySubject(){

        Threads thread1 = new Threads(null, Arrays.asList("parentId"), "parentId",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("", Arrays.asList("parentId"), "parentId",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread3 = new Threads("    ", Arrays.asList("parentId"), "parentId",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);
        boolean testResult3 = sut.isValid(thread3);

        assertFalse(testResult1);
        assertFalse(testResult2);
        assertFalse(testResult3);
    }


}
