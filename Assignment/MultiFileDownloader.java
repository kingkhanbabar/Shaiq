package Assignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultiFileDownloader {

    public static void main(String[] args) throws InterruptedException {

        // URL list (add any URLs here)
        List<String> urlList = Arrays.asList(
                "https://example.com/file1.jpg",
                "https://example.com/file2.jpg",
                "https://wrongurl.com/invalid.jpg",  // will fail
                "https://example.com/file3.jpg"
        );

        // Create output folder
        File folder = new File("downloads");
        if (!folder.exists()) folder.mkdir();

        // Thread pool
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("Starting downloads...\n");

        for (String url : urlList) {
            executor.submit(() -> downloadWithRetry(url, 3)); // ⚡ Retry added
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);

        System.out.println("\nAll download tasks finished.");
    }

    private static void downloadWithRetry(String urlStr, int maxAttempts) {
        int attempt = 1;

        while (attempt <= maxAttempts) {

            System.out.println(Thread.currentThread().getName() +
                    " → Attempt " + attempt + " downloading: " + urlStr);

            boolean success = downloadFile(urlStr);

            if (success) {
                return; // download completed
            }

            attempt++;

            // Wait before retry
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ignored) { }
        }

        System.out.println(" Download permanently failed after " + maxAttempts +
                " attempts: " + urlStr);
    }

    private static boolean downloadFile(String urlStr) {
        try {
            URL url = new URL(urlStr);

            String fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1);

            try (InputStream in = url.openStream();
                 FileOutputStream out = new FileOutputStream("downloads/" + fileName)) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                System.out.println(" Downloaded successfully: " + urlStr);
                return true; // success
            }

        } catch (Exception e) {
            System.out.println("Failed: " + urlStr + " | Reason: " + e.getMessage());
            return false; // failed
        }
    }
}
