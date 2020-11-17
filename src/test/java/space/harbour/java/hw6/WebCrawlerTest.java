package space.harbour.java.hw6;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;


public class WebCrawlerTest {
    @Test
    public void test1() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!WebCrawler.toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            if (executorService.isTerminated()) {
                System.err.println("Timed out. Shutting down.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for executor shutdown.");
            Thread.currentThread().interrupt();
        }
        System.out.println(WebCrawler.alreadyVisited);
        System.out.println(WebCrawler.toVisit);
        Assert.assertEquals(4, WebCrawler.alreadyVisited.size());
    }

    @Test
    public void test2() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!WebCrawler.toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            if (executorService.isTerminated()) {
                System.err.println("Timed out. Shutting down.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for executor shutdown.");
            Thread.currentThread().interrupt();
        }
        System.out.println(WebCrawler.alreadyVisited);
        System.out.println(WebCrawler.toVisit);
        Assert.assertTrue(null, WebCrawler.toVisit.isEmpty());
    }

    @Test
    public void test3() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!WebCrawler.toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            if (executorService.isTerminated()) {
                System.err.println("Timed out. Shutting dow   n.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for executor shutdown.");
            Thread.currentThread().interrupt();
        }
        System.out.println(WebCrawler.alreadyVisited);
        System.out.println(WebCrawler.toVisit);
        Assert.assertFalse(WebCrawler.alreadyVisited.isEmpty());
    }
}