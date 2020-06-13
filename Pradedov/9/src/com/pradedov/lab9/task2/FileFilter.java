package com.pradedov.lab9.task2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FileFilter extends SimpleFileVisitor {
    public static final int BUFFER_SIZE = 256;
    public static final int FILE_CONTENT_LENGTH = 10;
    private final String rootDirectory;
    private final Map<String, Long> fileSizeMap = new HashMap<>();

    public FileFilter(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void run() {
        try {
            Files.walkFileTree(Paths.get(this.rootDirectory).toAbsolutePath(), this);
        } catch (IOException e) {
            System.out.println("Опять сломал? А ты, случайно, не тестировщик?");
        }

        System.out.println("\n");
        printMinFiles();
        printMaxFiles();
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) {
        fileSizeMap.put(((Path) file).toAbsolutePath().toString(), attrs.size());
        return FileVisitResult.CONTINUE;
    }

    public Map<String, Long> getFileSizes() {
        return fileSizeMap;
    }

    private void printMinFiles() {
        Long minFileSize = Collections.min(fileSizeMap.values());

        System.out.println("Files with min size(" + minFileSize + "):");
        for(Map.Entry<String, Long> file : fileSizeMap.entrySet()) {
            if(file.getValue().equals(minFileSize)) {
                System.out.println("File: " + file.getKey());
                System.out.print("Content: ");
                printFileContent(file.getKey(), -1);
            }
        }
    }

    private void printMaxFiles() {
        Long maxFileSize = Collections.max(fileSizeMap.values());
        System.out.println("Files with max size(" + maxFileSize + "):");

        for(Map.Entry<String, Long> file : fileSizeMap.entrySet()) {
            if(file.getValue().equals(maxFileSize)) {
                System.out.println("File: " + file.getKey());
                System.out.print("Content(first " + FILE_CONTENT_LENGTH + " symbols): ");
                printFileContent(file.getKey(), FILE_CONTENT_LENGTH);
            }
        }
    }

    private void printFileContent(String pathName, int length) {
        byte[] fileBuffer = new byte[BUFFER_SIZE];
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(pathName))) {
            // length < 0 assumes entire file content
            if (length < 0) {
                while (bufferedInputStream.read(fileBuffer) != -1) {
                    System.out.print(new String(fileBuffer));
                }
            }
            else {
                bufferedInputStream.read(fileBuffer, 0, length);
                System.out.print(new String(fileBuffer));
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Опять сломал что-ли? Очередного форматирования жесткий диск не переживет.");
        }
    }
}
