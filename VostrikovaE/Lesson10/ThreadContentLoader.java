package VostrikovaE.Lesson10;

import java.util.concurrent.ConcurrentMap;


public class ThreadContentLoader implements Runnable {

    private final ConcurrentMap<String, byte[]> mapContent;
    private final ConcurrentMap<String, Double> mapTime;
    private final SimpleContentLoader simpleContentLoader;


    public ThreadContentLoader(ConcurrentMap<String, byte[]> mapContent, ConcurrentMap<String, Double> mapTime, String url){
        this.mapContent=mapContent;
        this.mapTime=mapTime;
        simpleContentLoader =new SimpleContentLoader(url);
    }

    @Override
    public void run() {
        long startTime;
        double timeLoaded;
        startTime=System.nanoTime(); // время в наносекундах
        byte[] data= simpleContentLoader.getData();
        timeLoaded=(System.nanoTime()-startTime)/1000000.0; //в милисекунды
        mapContent.put(simpleContentLoader.getUrl(), data);
        mapTime.put(simpleContentLoader.getUrl(), timeLoaded);
    }
}
