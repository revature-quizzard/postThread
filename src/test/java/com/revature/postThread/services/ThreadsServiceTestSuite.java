package com.revature.postThread.services;
import com.revature.postThread.models.Threads;

import com.revature.postThread.repositories.ThreadsRepo;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenNullThread(){
        Threads thread = null;

        boolean testResult = sut.isValid(thread);

        assertFalse(testResult);
    }

    /**
     * author - Charles Mettee
     */
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

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenAncestorsNullOrNotSizeOne(){
        Threads thread1 = new Threads("subject", null, "parentId",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("subject", Arrays.asList("ancestor1Id", "ancestor2Id"),
                "parentId", "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);

        assertFalse(testResult1);
        assertFalse(testResult2);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyParent(){
        Threads thread1 = new Threads("subject", Arrays.asList("parentId"), null,
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("subject", Arrays.asList("parentId"), "",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread3 = new Threads("subject", Arrays.asList("parentId"), "     ",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);
        boolean testResult3 = sut.isValid(thread3);

        assertFalse(testResult1);
        assertFalse(testResult2);
        assertFalse(testResult3);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyDescription(){
        Threads thread1 = new Threads("subject", Arrays.asList("parentId"), "parentId",
                null, "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("subject", Arrays.asList("parentId"), "parentId",
                "", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread3 = new Threads("subject", Arrays.asList("parentId"), "parentId",
                "     ", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);
        boolean testResult3 = sut.isValid(thread3);

        assertFalse(testResult1);
        assertFalse(testResult2);
        assertFalse(testResult3);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyOwner(){
        Threads thread1 = new Threads("subject", Arrays.asList("parentId"), "parentId",
                "description", null, Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("subject", Arrays.asList("parentId"), "parentId",
                "description", "", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread3 = new Threads("subject", Arrays.asList("parentId"), "parentId",
                "description", "    ", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);
        boolean testResult3 = sut.isValid(thread3);

        assertFalse(testResult1);
        assertFalse(testResult2);
        assertFalse(testResult3);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsTrue_givenValidThread(){
        Threads thread1 = new Threads("subject", Arrays.asList("parentId"), "parentId",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult = sut.isValid(thread1);

        assertTrue(testResult);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void addThreads_throwsException_givenInvalidThread(){
        Threads thread = null;
        boolean testResult = false;

        try{
            sut.addThreads(thread);
        } catch (Exception e){
            testResult = true;
        }

        assertTrue(testResult);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void addThreads_isSuccessful_givenValidThread(){

        Threads subforum = new Threads("subforumId", "Java", Arrays.asList(), "",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        Threads thread = new Threads("Recursion in Java", Arrays.asList("subforumId"), "subforumId",
                "description", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        when(mockThreadsRepo.getSubforum(any())).thenReturn(subforum);

        try{
            sut.addThreads(thread);
        } catch (Exception e){

        }

        verify(mockThreadsRepo, times(1)).addThreads(thread);
    }

}
