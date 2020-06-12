package iljicheva.lesson9.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static iljicheva.lesson9.task1.Main.ROOT_PATH;

class Utils {
    private static List<String> listCreatedFilesPaths = new ArrayList<>();

    static String createTreeDirs() throws IOException {
        StringBuilder dirPath = new StringBuilder(ROOT_PATH);
        final int numDirs = new Random().nextInt(3);
        for (int i = 0 ; i <= numDirs; i++){
            dirPath.append("\\").append(i + 1);
        }
        File file = new File(dirPath.toString());
        if (!file.exists()){
            if(file.mkdirs()) {
                System.out.println("Directories was created");
            } else {
                throw new IOException("Directories "  + " weren't created");
            }
        } else {
            System.out.println("Directories already exist");
        }
        return dirPath.toString();
    }

    static void createFiles() throws IOException {
        Stream<Path> path = Files.walk(Paths.get(ROOT_PATH));
        for (Object dir : path.toArray()){
            final int numFiles = new Random().nextInt(2) + 1;
            for(int i = 0; i < numFiles; i++){
                final String pathFile = dir.toString() + "\\File_0" + (i+1) + ".txt";
                File file = new File(pathFile);
                if(file.createNewFile()){
                    listCreatedFilesPaths.add(pathFile);
                    fillFile(pathFile);
                    System.out.println("File " + pathFile + " is created" + "\n size: " + file.length());
                } else if(file.exists()) {
                    System.out.println("File already exists");
                } else {
                    throw new IOException("File wasn't created");
                }
            }
        }
    }

    static void deleteDirectory(String rootPath) {
        File dir = new File(rootPath);
        if (dir.isDirectory()) {
            for (File item : Objects.requireNonNull(dir.listFiles())) {
                deleteDirectory(item.getAbsolutePath());
            }
            if(dir.delete()){
                System.out.println("The directory " + dir.getName() + " was deleted");
            }
        }
        else {
            if(dir.delete()){
                System.out.println("The file " + dir.getName() + " was deleted");
            }
        }
    }

    private static void fillFile(String fileName) throws IOException {
        try (FileWriter fos = new FileWriter(fileName)) {
            int numDig = new Random().nextInt(190) + 10;
            for (int i = 0; i < numDig; i++){
                int digit = new Random().nextInt(10);
                fos.write(digit + ", ");
                fos.flush();
            }
        }
    }

    static List findSmallestFile(){
        List<String> smallestFiles = new ArrayList<>();
        File smallFile = new File(listCreatedFilesPaths.get(0));
        long lengthSmallFile = smallFile.length();
        for (String path : listCreatedFilesPaths) {
            File file = new File(path);
            if(file.length() < lengthSmallFile){
                lengthSmallFile = file.length();
            }
        }
        for (String path : listCreatedFilesPaths) {
            File file = new File(path);
            if(file.length() == lengthSmallFile){
                smallestFiles.add(path);
            }
        }
        return smallestFiles;
    }

    static List findBiggestFile(){
        List<String> biggestFiles = new ArrayList<>();
        File bigFile = new File(listCreatedFilesPaths.get(0));
        long lengthBigFile = bigFile.length();
        for (String path : listCreatedFilesPaths) {
            File file = new File(path);
            if(file.length() > lengthBigFile){
                lengthBigFile = file.length();
            }
        }
        for (String path : listCreatedFilesPaths) {
            File file = new File(path);
            if(file.length() == lengthBigFile){
                biggestFiles.add(path);
            }
        }
        return biggestFiles;
    }

    static void printSizeFile(String pathFile){
        File file = new File(pathFile);
        System.out.println(file.length());
    }

    static void printFileContent(String fileName) throws IOException {
        byte[] bufferedBytes = new byte[1000];
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            while (bufferedInputStream.read(bufferedBytes) != -1) {
                System.out.print(new String(bufferedBytes));
            }
            System.out.println();
        }
    }

    static void printFirstNCharsFromFile(String fullFileName, byte[] bytes) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fullFileName))) {
            System.out.println(new String(bytes, 0, bufferedInputStream.read(bytes)));
        }
    }
}
