package com.mera.gokhmak.les10;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    static Queue<String> queue = new ConcurrentLinkedQueue<>();
    static Queue<Long> sizes = new ConcurrentLinkedQueue<>();

    static void fillQueue() {
        queue.offer("https://ru.wikipedia.org/wiki/Заглавная_страница");
        queue.offer("https://yandex.ru");
        queue.offer("https://habr.com/ru/");
    }

    public static void main(String[] args) throws InterruptedException {
        fillQueue();

        Runnable pageLoader = () -> {
            System.out.println("Start new routine");
            String link = null;
            long total = 0;
            int bte = 0;
            while ((link = queue.poll()) != null) {
                try (InputStream in = new URL(link).openStream()) {
                    while ((bte = in.read()) != -1) {
                        total += bte;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sizes.add(total);
                System.out.println("Page " + link + " Size=" + total + " bytes");
            }
            System.out.println("Finished routine");
        };

        Thread t = new Thread(pageLoader);
        long start = System.currentTimeMillis();
        t.start();
        t.join();
        System.out.println("Single thread duration is " + (System.currentTimeMillis() - start) + " ms");
        long totalSize = 0;
        Long size = null;
        while ((size = sizes.poll()) != null) {
            totalSize += size;
        }
        System.out.println("Total size is " + totalSize + " bytes");

        // Multi threading mode
        fillQueue();

        Thread[] pool = new Thread[] { new Thread(pageLoader), new Thread(pageLoader), new Thread(pageLoader) };
        start = System.currentTimeMillis();
        for (Thread thread : pool) {
            thread.start();
        }
        for (Thread thread : pool) {
            thread.join();
        }
        System.out.println("Multi thread duration is " + (System.currentTimeMillis() - start) + " ms");
        totalSize = 0;
        while ((size = sizes.poll()) != null) {
            totalSize += size;
        }
        System.out.println("Total size is " + totalSize + " bytes");
    }
}
