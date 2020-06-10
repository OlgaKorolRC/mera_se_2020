package VostrikovaE.Lesson9.Exercise2;

import VostrikovaE.Lesson9.Exercise1.FileCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileAnalyzer {

    private final FileCreator fileCreator;
    public FileAnalyzer(FileCreator fileCreator){
        this.fileCreator=fileCreator; // получим FileCreator для того чтобы
    }

    public void printSmallFile(){
       List<Path> allFilesPath= fileCreator.getAllFiles(); // список всех путей
        List<File> minimalSizeFiles=getMinimalFileList(allFilesPath);

        for(File file: minimalSizeFiles){
            System.out.println("Файл "+file.toPath()+" с минимальным размером: " + file.length() + " байт");
            System.out.println("Содержит данные: ");
            try {
                BufferedReader reader = Files.newBufferedReader(file.toPath());
                reader.lines().forEach(System.out::println);
                reader.close();
                System.out.println(" ---------------------------  ");
            }catch (IOException exception){
                System.out.println("Не удалось считать содержимое файла");
            }
        }

    }

    public void printBigFile(int countSymbolsToRead){
        List<Path> allFilesPath= fileCreator.getAllFiles(); // список всех путей
        List<File> maximalSizeFiles=getMaximalFileList(allFilesPath);

        for(File file: maximalSizeFiles){
            System.out.println("Файл "+file.toPath()+" с максимальным размером: " + file.length() + " байт");
            System.out.println("Содержит данные в первых "+countSymbolsToRead+" символах: ");
            try {
                BufferedReader reader = Files.newBufferedReader(file.toPath());
                char[] readData = new char[countSymbolsToRead];
                reader.read(readData, 0, countSymbolsToRead);
                reader.close();
                System.out.println(String.copyValueOf(readData));
                System.out.println(" ---------------------------  ");
            }catch (IOException exception){
                System.out.println("Не удалось считать содержимое файла");
            }
        }

    }





    //найти в списке путей минимальные по размеру файлы
    private List<File> getMinimalFileList(List<Path> paths){
        List<File> minimalSizeFile=new ArrayList<>(); //массив файлов с минимальным размером
        Map<File, Long> sortedMap=new TreeMap<>(
                (o1, o2) -> Long.compare(o1.length(), o2.length()) //IntelliJ свернула все в лямбду
        );
        //заполнили карту всеми файлами с их размерами
        for (Path currFile: paths){
            sortedMap.put(currFile.toFile(), currFile.toFile().length()); // положим файл в карту
        }

        Long minimalSize = null;
        //нашли минимальный по размеру файл
        for (Map.Entry<File, Long> pair: sortedMap.entrySet()){
            if (minimalSize==null){ //первая итерация, проинициализирует minimalSize значением первого элемента
                minimalSize=pair.getValue();
                minimalSizeFile.add(pair.getKey()); // добавим первый элемент в список
            } else {
                //если предыдущий элемент в карте имеет такой же размер, добавим его в массив, карта отсортирована, поэтому первый элемент будет не больше всех остальных
                if (pair.getValue().equals(minimalSize)){
                    minimalSizeFile.add(pair.getKey());
                }
            }

        }
        return minimalSizeFile;
    }

    //найти в списке путей максимальные по размеру файлы
    private List<File> getMaximalFileList(List<Path> paths){
        List<File> maximalFilesSize = new ArrayList<>(); //массив файлов с минимальным размером
        Map<File, Long> sortedMap=new TreeMap<>(
                (o1, o2) -> (-1)*Long.compare(o1.length(), o2.length()) //умножили на -1, чтобы получить обратный порядок
        );
        //заполнили карту всеми файлами с их размерами
        for (Path currFile: paths){
            sortedMap.put(currFile.toFile(), currFile.toFile().length()); // положим файл в карту
        }

        Long maximalSize = null;
        //нашли минимальный по размеру файл
        for (Map.Entry<File, Long> pair: sortedMap.entrySet()){
            if (maximalSize==null){ //первая итерация, проинициализирует minimalSize значением первого элемента
                maximalSize=pair.getValue();
                maximalFilesSize.add(pair.getKey()); // добавим первый элемент в список
            } else {
                //если предыдущий элемент в карте имеет такой же размер, добавим его в массив, карта отсортирована, поэтому первый элемент будет не больше всех остальных
                if (pair.getValue().equals(maximalSize)){
                    maximalFilesSize.add(pair.getKey());
                }
            }

        }
        return maximalFilesSize;
    }
}
