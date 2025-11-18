package Babar;

//package MultiThreading.Java_Assignment;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Downloader implements Runnable{
    private BlockingQueue<String> queue;

    public Downloader(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    //
    @Override
    public void run() {
        try{
            while(true){
                String urlStr = queue.take();
                if(urlStr.equals("END")){
                    queue.put("END");
                    break;
                }
                Download(urlStr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    //Downloading the file Method
    private void Download(String urlStr){
        synchronized (this){  // synchronized block for thread-safety
            try{
                URL url = new URL(urlStr);
                String fileName = urlStr.substring(urlStr.lastIndexOf("/")+1);
                InputStream in = url.openStream();
                FileOutputStream out = new FileOutputStream("Download_" + fileName);

                byte[] buffer = new byte[1024];
                int byteRead;

                System.out.println(Thread.currentThread().getName()+" Downloading....."+fileName);
                while((byteRead = in.read(buffer)) != -1){
                    out.write(buffer,0,byteRead);
                }

                in.close();
                out.close();

                System.out.println(Thread.currentThread().getName()+" Download Successfully "+fileName);


            } catch (Exception e) {
                System.out.println("Download Failed : "+e.getMessage());;
            }
 }
    }
}