package space.harbour.java.hw6;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static space.harbour.java.hw6.WebCrawler.alreadyVisited;
import static space.harbour.java.hw6.WebCrawler.toVisit;

public class WebCrawlerTest {
    @Test
    public void test1() throws MalformedURLException {
        toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            if (executorService.isTerminated()) {
                System.err.println("Timed out waiting for executor to terminate cleanly. Shutting down.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for executor shutdown.");
            Thread.currentThread().interrupt();
        }
        System.out.println(alreadyVisited);
        System.out.println(toVisit);
        assertEquals(4, alreadyVisited.size());
    }

    @Test
    public void test2() throws MalformedURLException {
        toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            if (executorService.isTerminated()) {
                System.err.println("Timed out waiting for executor to terminate cleanly. Shutting down.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for executor shutdown.");
            Thread.currentThread().interrupt();
        }
        System.out.println(alreadyVisited);
        System.out.println(toVisit);
        assertTrue(null, toVisit.isEmpty());
    }

    @Test
    public void test3() throws MalformedURLException {
        toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            if (executorService.isTerminated()) {
                System.err.println("Timed out waiting for executor to terminate cleanly. Shutting dow   n.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for executor shutdown.");
            Thread.currentThread().interrupt();
        }
        System.out.println(alreadyVisited);
        System.out.println(toVisit);
        assertFalse(alreadyVisited.isEmpty());
    }
}