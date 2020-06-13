package com.pradedov.lab9.task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class DirectoryTreeCreator {
//    private final static String ROOT_DIR = "SE2020_LESSON9";
    private final String rootDirectory;
    private final static int MIN_NESTING = 1;
    private final static int MAX_NESTING = 3;
    private final static int MIN_FILE_NUMBER = 1;
    private final static int MAX_FILE_NUMBER = 3;
    public static final int MIN_NUMBER_OF_DIGITS = 10;
    public static final int MAX_NUMBER_OF_DIGITS = 200;
    public static final int UPPER_DIGIT_BOUNDARY = 10;
    public static final String FILE_NAME_BASE = "FILE_";

    public DirectoryTreeCreator(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void run() {
        try {
            createTree();
            walkThroughTree();
        }
        catch (IOException ex) {
            System.out.println("Ну что, доигрался? Теперь иди гугли, как диски форматировать.");
        }
    }

    public void createTree() throws IOException {
        createDirectory(rootDirectory);
        createSubdirectories(rootDirectory);
        createFiles(rootDirectory);
    }

    public void walkThroughTree() throws IOException {
        Path path = Paths.get("SE2020_LESSON9").toAbsolutePath();
        System.out.println("\nFiles under root directory " + path);
        Files.walkFileTree(path, new DirectoryTreeVisitor());
    }

    public void deleteTree(String rootDirectoryName) {
        File path = new File(rootDirectoryName);
        if (!path.isDirectory()) {
            path.delete();
        }
        else {
            File[] pathList = path.listFiles();
            if (null != pathList) {
                for (File item : pathList) {
                    deleteTree(item.getAbsolutePath());
                }
                path.delete();
            }
        }
    }

    private int getRandomValueInRange(int minValue, int maxValue) {
        Random random = new Random();
        return minValue + random.nextInt(maxValue + 1 - minValue);
    }

    private void createDirectory(String directoryName) {
        File path = new File(directoryName);
        if (path.exists()) {
            System.out.println("Directory already exists: " + path.getAbsolutePath());
            return;
        }

        if (!path.mkdir()) {
            System.out.println("Failed to create directory " + path.getAbsolutePath());
        }
    }

    private void createSubdirectories(String rootDirectoryName) {
        final int numOfDirs = getRandomValueInRange(MIN_NESTING, MAX_NESTING);

        System.out.println("Create " + numOfDirs + " sub-directories under " + rootDirectoryName);

        int startDirIndex = 1;
        for (int idx = 0; idx < numOfDirs; idx++) {
            rootDirectoryName += "/" + (startDirIndex + idx);
            createDirectory(rootDirectoryName);
        }
    }

    private void createFiles(String rootDirectoryName) throws IOException {
        File path = new File(rootDirectoryName);
        if(path.isDirectory()) {
            File[] pathList = path.listFiles();
            if (null != pathList) {
                for (File item : pathList) {
                    createFiles(item.getAbsolutePath());
                }

                int numOfFiles = getRandomValueInRange(MIN_FILE_NUMBER, MAX_FILE_NUMBER);
                int startFileIdx = 1;
                for (int idx = 0; idx < numOfFiles; idx++) {
                    String fileName = rootDirectoryName + "/" + FILE_NAME_BASE + (startFileIdx + idx) + ".txt";
                    createFile(fileName);
                    fillInTheFile(fileName);
                }
            }
        }
    }

    private void createFile(String fullFileName) throws IOException {
        File file = new File(fullFileName);
        System.out.println("Creating file " + fullFileName);
        if (!file.createNewFile()) {
            System.out.println("File already exists " + fullFileName);
        }
    }

    private void fillInTheFile(String fullFileName) throws IOException {
        try (FileWriter fos = new FileWriter(fullFileName)) {
            int numOfDigits = getRandomValueInRange(MIN_NUMBER_OF_DIGITS, MAX_NUMBER_OF_DIGITS);
            Random random = new Random();
            do {
                fos.write(Integer.toString(random.nextInt(UPPER_DIGIT_BOUNDARY)));
            } while(--numOfDigits > 0);
            fos.flush();
        }
    }
}
