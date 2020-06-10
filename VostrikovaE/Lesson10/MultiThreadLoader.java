package VostrikovaE.Lesson10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

public class MultiThreadLoader {

    private ConcurrentMap<String, byte[]> mapContent = new ConcurrentHashMap<>();
    private ConcurrentMap<String, Double> mapTimeLoad = new ConcurrentHashMap<>();
    private List<String> urls = new ArrayList<>(); // список url
    private List<ThreadContentLoader> contentLoaders=new ArrayList<>();
    public MultiThreadLoader(List<String> urls){
        if (urls!=null){
            for(String url: urls){
                this.urls.add(url);
                contentLoaders.add(new ThreadContentLoader(mapContent, mapTimeLoad, url));
            }
        }
    }

    public void getData(long timeout)  {
        double startTime;
        double downloadTime;
        List<Thread> threads=new ArrayList<>();
        for(ThreadContentLoader contentLoader: contentLoaders){
            threads.add(new Thread(contentLoader, contentLoader.toString())); // создаеим новый thread и добавляем в список
        }
        startTime= System.nanoTime();
        for (Thread thread: threads){
            thread.start(); //запускаем Thread
        }
        try {
            for (Thread thread : threads) {
                thread.join(timeout); //запускаем Thread
            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        downloadTime=(System.nanoTime()-startTime)/1000000;
        printInfo(downloadTime);
        mapContent.clear();
        mapTimeLoad.clear();
    }

    private void printInfo(double downloadtime){
        long AllSize= mapContent.entrySet().stream().reduce(0,(x,y)->{return x+y.getValue().length;}, (x,y)->x+y);
        mapContent.entrySet().forEach(x->System.out.println("Из адреса "+x.getKey()+ " было загружено: "+x.getValue().length+" байт"));
        System.out.println("-----------------------------------------");
        System.out.println("Общий размер принятых данных: "+AllSize);
        mapTimeLoad.entrySet().forEach(x->System.out.println("Загрузка из адреса "+x.getKey() + " заняла "+ x.getValue() + " милисекунд"));

        System.out.println("-----------------------------------------");
        System.out.println("Общее время загрузки: "+ downloadtime+ " милисекунд"); //здесь время загрузки не совпадает с суммарным временем
    }

    public Map<String, byte[]> getMapContent() {
        return mapContent;
    }

    public Map<String, Double> getMapTimeLoad() {
        return mapTimeLoad;
    }

}
