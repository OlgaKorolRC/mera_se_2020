package VostrikovaE.Lesson9.Exercise1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FileCreator {
    private String pathCreated="";
    private final int minCreatedFiles;
    private final int maxCreatedFiles;
    private final int minDigitToWrite;
    private final int maxDigitToWrite;
    private String startPathString ="";


    // Private конструктор, чтобы не  создавать объект с неправильными параметрами
    private FileCreator(int minCreatedFiles, int maxCreatedFiles, int minDigitToWrite, int maxDigitToWrite){
        this.minCreatedFiles=minCreatedFiles;
        this.maxCreatedFiles=maxCreatedFiles;
        this.minDigitToWrite=minDigitToWrite;
        this.maxDigitToWrite=maxDigitToWrite;
    }

    private void setStartPathString(String startPathString){
        this.startPathString = startPathString;
    }

    public static FileCreator newInstance(int minCreatedFiles, int maxCreatedFiles, int minDigitToWrite, int maxDigitToWrite, String startPathString) {
        if (minCreatedFiles<maxCreatedFiles && minCreatedFiles>0 && maxCreatedFiles>0){
            if (minDigitToWrite<maxDigitToWrite && minDigitToWrite>0 && maxDigitToWrite>0) {
                FileCreator fileCreator= new FileCreator(minCreatedFiles, maxCreatedFiles, minDigitToWrite, maxDigitToWrite);
                fileCreator.setStartPathString(startPathString);
                return fileCreator;
            } else {
                throw new IllegalArgumentException("Неправильное значение полей minDigitToWrite, maxDigitToWrite");
            }
        } else {
            throw new IllegalArgumentException("Неправильное значение полей minCreatedFiles, maxCreatedFiles");
        }

    }


    public void createFolders(int nestingLevel) // создать папку по пути path с количеством подпапок nestingLevel
    {
        String endPath=startPathString;
        int currentLevel=0; //текущий уровень вложености
        while(currentLevel<=nestingLevel) {
            if(currentLevel>0){
                endPath=endPath+"\\"+currentLevel;
            }
            try {
                createDirectory(endPath);
            } catch (IOException exception) {
                System.out.println("Не получилось создать папку: "+exception);
            }
            currentLevel++;
        }
    }



    public void createRandomFiles()  { //создать случайное количество файлов в папке startFolder и вложенных в нее
        Path startPath=Paths.get(startPathString); //получили стартовую папку
        List<Path> listPath=getAllSubDirectories(startPathString); //получили все поддиректории

           for(Path path: listPath) {
               createRandomFilesToDirectory(path); // создали случайные файлы во всех поддиректориях
           }
       }

    public void clearAll(){
        clearAllFiles(); //сначала очистили все файлы в директориях
        Path pathToDelete=Paths.get(startPathString);
        RecurrentClear(pathToDelete);

    }


    //очистка всех поддиректорий от файлов
    public void clearAllFiles(){
        List<Path> paths=getAllSubDirectoriesFiles(startPathString);
        for(Path currentPath: paths){
            clearDirectory(currentPath); //очистили все подпапки
        }

    }


    public void printAllFilePath(){
        List<Path> paths=getAllSubDirectoriesFiles(startPathString);
        for(Path currPath: paths){
            System.out.println(currPath.toString() + " размер "+ currPath.toFile().length() + " байт");
        }
        System.out.println(" ---------------------------  ");
    }

    public List<Path> getAllFiles() {
        return getAllSubDirectoriesFiles(startPathString);
    }


    //------------------------------------- Private методы -------------------------------------



    private static void createDirectory(String path) throws IOException {
        Path directoryPath= Paths.get(path);
        if(!Files.exists(directoryPath)){ //нет папки, создаем
            Files.createDirectory(directoryPath);
        }
    }

//не статичный потому что использует нестатичные поля
    private void writeRandomDataToFile(Path filePath) {
        Random rnd = new Random();
        int maxDigitWrite=rnd.nextInt(maxDigitToWrite-minDigitToWrite)+minDigitToWrite;
        int currWriteDigit=0;
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            while (currWriteDigit<=maxDigitWrite) {
                String writeLine=Integer.toString(getRandomValue(9)); //перевели в String, иначе запишется ASCII код символа
                writer.write(writeLine);
                currWriteDigit++;
            }
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            System.out.println("Файл "+filePath + " недоступен для записи" + '\n' + exception);
        }

    }

    //очистка директории
    private static void clearDirectory(Path path){
        try {
            Files.delete(path);
        } catch (IOException e){
            System.out.println("Не удалось очистить папку "+path);
        }
    }

    private static int getRandomValue(int Max){
        Random rnd = new Random();
        return rnd.nextInt(Max);
    }

    //получить все файлы во всех поддиректориях
    private static List<Path> getAllSubDirectoriesFiles(String directoryPath){
        Path startPath=Paths.get(directoryPath);
        List<Path> listPath=new ArrayList<>(); // вернем пустой лист если не получится
        if(Files.exists(startPath)) { //только если есть стартовая директория
            try {
                listPath = Files.walk(startPath).filter(Files::isRegularFile).collect(Collectors.toList()); //получили список всех путей к файлам во всех поддиректориях
            } catch (IOException exception) {
                System.out.println("Невозможно получить список поддиректорий для: " + startPath + '\n' + exception);
            }
        }
        return listPath;
    }


    //получить все поддиректории для path
    private static List<Path> getAllSubDirectories(String directoryPath){
        Path startPath=Paths.get(directoryPath);
        List<Path> listPath=new ArrayList<>(); // вернем пустой лист если не получится
        if(Files.exists(startPath)) { //только если есть стартовая директория
            try {
                listPath = Files.walk(startPath).collect(Collectors.toList()); //получили список всех поддиректорий
            } catch (IOException exception) {
                System.out.println("Невозможно получить список поддиректорий для: " + startPath + '\n' + exception);
            }
        }
        return listPath;
    }

    private static void RecurrentClear(Path path){
        File deletedFile=path.toFile();
        for (File file: deletedFile.listFiles()){
            RecurrentClear(file.toPath());
        }
        System.out.println("Папка " + deletedFile.toPath() + " была удалена" );
        deletedFile.delete();

    }

    private void createRandomFilesToDirectory(Path path)  {
        Random rnd = new Random();
        int countRandomFiles=rnd.nextInt(maxCreatedFiles-minCreatedFiles)+minCreatedFiles;
        for (int i=0; i<countRandomFiles; i++){
            try {
                Path filePath = Paths.get(path + "\\File_" + (i+1));
                Path createdPath=Files.createFile(filePath); //создали файл
                writeRandomDataToFile(createdPath); //записали что то в файл

            } catch (IOException exception){
                System.out.println("Файл "+ path + "\\File_" + (i+1) +" уже создан");
            }
        }
    }

}
