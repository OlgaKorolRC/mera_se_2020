import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;


class UrlSample extends Thread {

    private String url;

    // Конструктор, аргумент- url
    UrlSample(String url) {
        this.url = url;
    }

    static long time = System.currentTimeMillis();

    @Override
    public void run() { // Этот метод будет вызван при старте потока



        StringBuffer stringBuffer = new StringBuffer();

        int readOperations = 0;
        byte[] buffer = new byte[10];

        try (InputStream inputStream = new URL(url).openStream()) {
            int read = inputStream.read(buffer);
            while (read != -1) {
                stringBuffer.append(new String(buffer));
                read = inputStream.read(buffer);
                readOperations++;
            }

        } catch (Exception e) {}


        System.out.println("url = " + url + "\nВсего операций чтения " + readOperations + "\n size= " + stringBuffer.length());

        System.out.println(stringBuffer.toString());

        System.out.println("run time= " + Long.toString(System.currentTimeMillis() - time));


    }
}

class Sequential {
    public void sequentialReading(ArrayList < String > urls) {
        for (int i = 0; i < urls.size(); i++) {
            StringBuffer stringBuffer = new StringBuffer();

            int readOperations = 0;
            byte[] buffer = new byte[10];

            try (InputStream inputStream = new URL(urls.get(i)).openStream()) {
                int read = inputStream.read(buffer);
                while (read != -1) {
                    stringBuffer.append(new String(buffer));
                    read = inputStream.read(buffer);
                    readOperations++;
                }

            } catch (Exception e) {}

            System.out.println(stringBuffer.toString());

            System.out.println("\nВсего операций чтения " + readOperations + "\n size= " + stringBuffer.length());


        }

    }
}




public class Task1 {

    // Класс для демонстрации работы потока
    public static void main(String[] args) throws IOException {


        ArrayList < String > urls = new ArrayList < > (Arrays.asList("http://proglang.su/java/io-and-files-and-directories-filereader-class", "http://www.frolov-lib.ru/programming/javasamples/vol8/vol8_2/index.html", "http://grib-info.ru/syedobnie/podberezovik.html"));



        long time = System.currentTimeMillis();
        Sequential sec = new Sequential();
        sec.sequentialReading(urls);
        System.out.println("sequentialReading time= " + Long.toString(System.currentTimeMillis() - time));

        UrlSample url1 = new UrlSample(urls.get(0));
        UrlSample url2 = new UrlSample(urls.get(1));
        UrlSample url3 = new UrlSample(urls.get(2));

        System.out.println("\n");

        url1.start();
        url2.start();
        url3.start();


    }
}
