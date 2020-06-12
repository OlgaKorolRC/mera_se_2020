package iljicheva.lesson10;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final URL url1 = new URL("https://javarush.ru/quests");
        URL url2 = new URL("https://javarush.ru/quizzes");
        URL url3 = new URL("https://javarush.ru/tasks");

        Downloader downloader = new Downloader();
        downloader.downloadHtmlPage(url1);
        downloader.downloadHtmlPage(url2);
        downloader.downloadHtmlPage(url3);
        /*Downloader httpSecond = new Downloader();
        httpSecond.downloadHtmlPage(new URL("https://javarush.ru/groups/posts/2181-vlozhennihe-vnutrennie-klassih"));
        Downloader httpThird = new Downloader();
        httpThird.downloadHtmlPage(new URL("https://javarush.ru/groups/posts/2181-vlozhennihe-vnutrennie-klassih"));*/

        System.out.println("total download time for 3 URL = " + (downloader.getDownloadTime()));
        System.out.println("total download bytes for 3 URL = " + (downloader.getDownloadsBytes()));

        final long startTime = System.nanoTime();
        Downloader downloaderFirst = new Downloader();
        Downloader downloaderSecond = new Downloader();
        Downloader downloaderThird = new Downloader();
        Thread firstThred = new Thread(()->{
            try {
                downloaderFirst.downloadHtmlPage(url1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread secondThred = new Thread(()->{
            try {
                downloaderSecond.downloadHtmlPage(url1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread thirdThred = new Thread(()->{
            try {
                downloaderThird.downloadHtmlPage(url1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        firstThred.start();
        secondThred.start();
        thirdThred.start();

        firstThred.join();
        secondThred.join();
        thirdThred.join();

        final long totalDownloadedBytes = downloaderFirst.getDownloadsBytes() + downloaderSecond.getDownloadsBytes() + downloaderThird.getDownloadsBytes();
        System.out.println("Total time = " + (System.nanoTime() - startTime)/1000000);
        System.out.println("Total bytes = " + totalDownloadedBytes);
    }
}
