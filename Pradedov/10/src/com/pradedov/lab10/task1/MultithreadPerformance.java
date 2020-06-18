package com.pradedov.lab10.task1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CommonStatistics {
    /**
     * total loading time of all pages loaded (ms)
     */
    private final long loadingTime;

    /**
     * total size of all pages loaded (bytes)
     */
    private final long size;

    public CommonStatistics(long loadingTime, long size) {
        this.loadingTime = loadingTime;
        this.size = size;
    }

    public long getLoadingTime() {
        return loadingTime;
    }

    public long getSize() {
        return size;
    }
}

public class MultithreadPerformance {
    private static final int NS_TO_MS_DIVIDER = 1_000_000;
    private final List<String> links = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();
    private final Map<String, Integer> pageSizeMap = new ConcurrentHashMap<>();

    public MultithreadPerformance(String [] httpAddresses) {
        links.addAll(Arrays.asList(httpAddresses));
    }

    public CommonStatistics testSequentialLoading() {
        performTestReset();

        final long startTime = System.nanoTime();
        for (String httpAddress: links) {
            loadPage(httpAddress);
        }
        final long loadingTime = (System.nanoTime() - startTime) / NS_TO_MS_DIVIDER;
        System.out.println("Loading time:   " + loadingTime);

        return new CommonStatistics(loadingTime, getTotalSize());
    }

    public CommonStatistics testParallelLoading() {
        performTestReset();

        final long startTime = System.nanoTime();

        for (String address : links) {
            threads.add(new Thread(() -> loadPage(address)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Something has gone wrong while we were waiting for thread execution complete: " + ex);
        }

        final long loadingTime = (System.nanoTime() - startTime) / NS_TO_MS_DIVIDER;
        System.out.println("Loading time:   " + loadingTime);

        return new CommonStatistics(loadingTime, getTotalSize());
    }

    private void loadPage(String httpAddress) {
        int pageSize = 0;
        System.out.println("Loading page " + httpAddress + " has started");
        final long startTime = System.nanoTime();
        try (InputStream input = new URL(httpAddress).openStream()) {
            while (input.read() != -1) {
                ++pageSize;
            }
        }
        catch (IOException ex) {
            System.out.println("Something has gone wrong while opening/reading stream: " + ex);
        }
        final long loadingTime = (System.nanoTime() - startTime) / NS_TO_MS_DIVIDER;
        System.out.println("Loading page " + httpAddress + " has finished in " + loadingTime + " ms");
        pageSizeMap.put(httpAddress, pageSize);
    }

    private void performTestReset() {
        threads.clear();
        pageSizeMap.clear();
    }

    private long getTotalSize() {
        long totalSize = 0;
        for (Map.Entry<String, Integer> entry : pageSizeMap.entrySet()) {
            totalSize += entry.getValue();
        }
        return totalSize;
    }
}
