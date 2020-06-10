package VostrikovaE.Lesson10;


import java.util.ArrayList;
import java.util.List;

public class MainLesson10 {

    public static void main(String[] args)  {
            int testBadIterator =0;
            List<String> urls= new ArrayList<>();
            final long downloadTimeout=10000;

            urls.add("https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html");
            urls.add("https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/package-summary.html");
            urls.add("https://docs.oracle.com/javase/tutorial/networking/urls/index.html");
            urls.add("Invalid url");
            urls.add("http://notAvaliableSite.com");

            System.out.println('\n' + " -----------------  Однопоточное приложение  ----------------------" + '\n');

            SingleThreadLoader singleThreadLoader = new SingleThreadLoader(urls);
            singleThreadLoader.getData();

            System.out.println('\n' + "-----------------  Многопоточное приложение  ----------------------" + '\n');
            MultiThreadLoader nonSingleThreadLoader = new MultiThreadLoader(urls);
            nonSingleThreadLoader.getData(downloadTimeout);
       }

}