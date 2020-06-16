package com.company.Lesson11.Lecture9;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PathMain {
    static FileWriter fileWriter;

    private final static int MIN_NUM_CHAR = 10;
    private final static int MAX_NUM_CHAR = 200;
    private final static int ALL_NUMBERS = 10;

//Ничего не успеваю в человеческом виде сделать((
 // Это Lesson9 Task 1 (получились два не взаимосвязанных куска с Task 2)

    public static void main(String[] args) throws IOException {
        String path= "C:\\Users\\smilovid\\Desktop\\java\\Date\\10\\SE2020_LESSON9";
        int numFiles=3;
        File dir = new File(path);
        boolean created = dir.mkdir();
        if(created) {
            System.out.println("Folder has been created" + path);
           fileCreate(path,numFiles);
        }

        Random random=new Random();
        int numFolders=random.nextInt(3)+1;
        System.out.println("numFolders" + numFolders);
        for(int i=1;i<=numFolders;i++){
            path+= "\\" + i;
            dir = new File(path);
            created = dir.mkdir();
            if(created) {
                System.out.println("Folder has been created" + path);
                fileCreate(path,numFiles);
            }
        }
    }

    public static void fileCreate(String path, int numFiles) throws IOException {
            for(int i=1;i<=numFiles;i++){
            String filePath= path + "\\File_" + i + ".txt";
            System.out.println("filePath" + filePath);
            fileWriter = new FileWriter(filePath);
            writeCharFile(filePath);

        }
    }

    public static void writeCharFile(String filePath) throws IOException {
        Random random = new Random();
        try (FileOutputStream fos = new FileOutputStream(filePath)){
            int numChar = random.nextInt(MAX_NUM_CHAR - MIN_NUM_CHAR) + MIN_NUM_CHAR;
            for (int i=0; i<numChar;i++){
                fos.write(String.valueOf(random.nextInt(ALL_NUMBERS)).getBytes());
            }
        }

    }
}
