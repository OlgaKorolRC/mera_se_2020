package iljicheva.lesson10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Downloader {
    private long downloadsBytes;
    private long downloadTime;

    void downloadHtmlPage(URL url) throws IOException {
        long beginTime = System.nanoTime();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                downloadsBytes += (long) line.length();
            }
            downloadTime += (System.nanoTime() - beginTime)/1000000;
        }
    }

    public long getDownloadsBytes() {
        return downloadsBytes;
    }

    public long getDownloadTime() {
        return downloadTime;
    }
}
