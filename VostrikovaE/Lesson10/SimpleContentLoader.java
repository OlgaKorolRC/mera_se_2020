package VostrikovaE.Lesson10;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/*
* Класс для загрузки и получения информации о URL
*
* */
public class SimpleContentLoader {

    private final String url;
    private URL urlConnector;

    public SimpleContentLoader(String url) {
        this.url=url;
    }

    public byte[] getData(){
        byte[] data = new byte[0];
        try {
            urlConnector = new URL(url);
            InputStream stream = urlConnector.openStream();
             data = stream.readAllBytes();
            stream.close();
        }catch (MalformedURLException e){
            System.out.println("Некорректный адрес "+ url+'\n'+e+'\n');
        } catch (IOException e){
            System.out.println("Нет соединения с адресом "+ url+'\n'+e+'\n');
        }
        return data;
    }

    public String getUrl() {
        return url;
    }
}
