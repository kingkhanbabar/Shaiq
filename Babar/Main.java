package Babar;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Add file URLs
        queue.put("https://example-files.online-convert.com/document/txt/example.txt");
        queue.put("https://example-files.online-convert.com/raster%20image/png/example.png");
        queue.put("https://doc-0g-6g-apps-viewer.googleusercontent.com/viewer/secure/CA11 Algorithm and Program Design lecture#3.pdf");
        queue.put("END"); // signal end

        //Task1
        Downloader task1 = new Downloader(queue);
        //Task2
        Downloader task2 = new Downloader(queue);

        Thread t1 = new Thread(task1,"Thread-1");
        Thread t2 = new Thread(task2,"Thread-2");
        Thread t3 = new Thread(new Downloader(queue),"Thread -3");

        t1.start();
        t2.start();
        t3.start();
    }
}
