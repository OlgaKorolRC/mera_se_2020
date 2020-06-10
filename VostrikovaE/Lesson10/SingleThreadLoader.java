package VostrikovaE.Lesson10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//класс загрузчика ссылок для одного потока
public class SingleThreadLoader {

    private List<String> urls = new ArrayList<>();
    private List<SimpleContentLoader> simpleContentLoaders = new ArrayList<>();
    private final Map<String, byte[]> mapContent = new HashMap<>(); // однопоточное приложение можно обычный HashMap
    private final Map<String, Double> mapTimeLoad = new HashMap<>();

    public SingleThreadLoader(List<String> urls){
        if (urls!=null){
            for(String url: urls){
                this.urls.add(url);
            }
            for(String url: urls){
                 simpleContentLoaders.add(new SimpleContentLoader(url)); // Добавляем contentLoaders
             }
        }
    }

    public Map<String, byte[]> getMapContent() {
        return mapContent;
    }

    public Map<String, Double> getMapTimeLoad() {
        return mapTimeLoad;
    }

    public void getData(){
        long startTime;
        double timeLoaded;
        byte[] data;
        for (SimpleContentLoader simpleContentLoader : simpleContentLoaders){
            startTime=System.nanoTime(); // время в наносекундах
            data= simpleContentLoader.getData();
            timeLoaded=(System.nanoTime()-startTime)/1000000.0; //в милисекунды
            mapContent.put(simpleContentLoader.getUrl(), data);
            mapTimeLoad.put(simpleContentLoader.getUrl(), timeLoaded);

        }
        printInfo();
    }

    private void printInfo(){
        long AllSize= mapContent.entrySet().stream().reduce(0,(x,y)-> x+y.getValue().length, (x, y)->x+y);
        mapContent.entrySet().forEach(x->System.out.println("Из адреса "+x.getKey()+ " было загружено: "+x.getValue().length+" байт"));
        System.out.println("-----------------------------------------");
        System.out.println("Общий размер принятых данных: "+AllSize);
        mapTimeLoad.entrySet().forEach(x->System.out.println("Загрузка из адреса "+x.getKey() + " заняла "+ x.getValue() + " милисекунд"));
        double AllTime=mapTimeLoad.entrySet().stream().reduce(0.0,(x,y)-> x+y.getValue(), (x, y)->x+y);

        System.out.println("-----------------------------------------");
        System.out.println("Общее время загрузки: "+ AllTime+ " милисекунд"); //здесь время загрузки совпадает с суммарным временем загрузки
    }




}
