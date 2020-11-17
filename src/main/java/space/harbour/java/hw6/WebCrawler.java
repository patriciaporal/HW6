package space.harbour.java.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
    static ConcurrentLinkedQueue<URL> toVisit = new ConcurrentLinkedQueue<>();
    static CopyOnWriteArraySet<URL> alreadyVisited = new CopyOnWriteArraySet<>();

    public static class UrlVisitor implements Runnable {
        public static String getContentOfWebPage(URL url) {
            final StringBuilder content = new StringBuilder();

            try (InputStream is = url.openConnection().getInputStream();
                 InputStreamReader in = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(in)) {
                String inputLine;
                while (( inputLine = br.readLine() ) != null)
                    content.append(inputLine);
            } catch (IOException e) {
                System.out.println("Failed to retrieve content of " + url.toString());
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        public void run() {
            synchronized (toVisit) {
                synchronized (alreadyVisited) {
                    URL url = toVisit.poll();
                    alreadyVisited.add(url);
                    String content = getContentOfWebPage(url);

                    String regex = "\\b(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]" + "*[-a-zA-Z0-9+&@#/%=~_|]";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(content);

                    while (matcher.find()) {
                        try {
                            URL newURL = new URL(matcher.group());
                            if (!toVisit.contains(newURL) && !alreadyVisited.contains(newURL)) {
                                toVisit.add(newURL);
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

        public static void main(String[] args) throws MalformedURLException {
            toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (!toVisit.isEmpty()) {
                executorService.submit(new UrlVisitor());
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
        }
    }
