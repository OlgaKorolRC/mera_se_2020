package com.mera.gokhmak.les9;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;

public class Tasks {
    final static String PATH_PREFIX = "D:/test/SE2020_LESSON9";
    final static String DIR_PATTERN = "Directory_#";
    final static String FILE_PATTERN = "File_#";
    private static SortedSet<AFile> sortedFiles = new TreeSet<>();

    static final class AFile implements Comparable<AFile> {
        public String path;
        public Long size;

        public AFile(String path, Long size) {
            this.path = path;
            this.size = size;
        }

        @Override
        public int compareTo(AFile o) {
            return this.size.compareTo(o.size);
        }

        @Override
        public int hashCode() {
            return Objects.hash(path, size);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            AFile file = (AFile) obj;
            return Objects.equals(path, file.path) &&
                    size == file.size;
        }

        @Override
        public String toString() {
            return "AFile{" +
                    "path='" + path + '\'' +
                    ", size=" + size +
                    '}';
        }
    }

    static void recursiveDelete(final File rootDir) {
        if (!rootDir.exists()) {
            return;
        }
        if (rootDir.isDirectory()) {
            for (File f : rootDir.listFiles()) {
                recursiveDelete(f);
            }
        }
        rootDir.delete();
    }

    static void recursivePrintFiles(final File rootDir) {
        if (!rootDir.exists()) {
            return;
        }
        if (rootDir.isDirectory()) {
            for (File f : rootDir.listFiles()) {
                recursivePrintFiles(f);
            }
        } else if (rootDir.isFile()) {
            System.out.println(rootDir.getAbsolutePath());
        }
    }

    static boolean createDirs(final String rootDir) {
        StringBuilder b = new StringBuilder(rootDir);
        for (int i = 1; i <= (int)(Math.random() * 3) + 1; i++) {
            b.append("/" + DIR_PATTERN.replaceAll("#", Integer.toString(i)));
        }
        File f = new File(b.toString());
        if (f.exists()) {
            return true;
        }
        return f.mkdirs();
    }

    static void createFiles(final String rootDir) throws IOException {
        File f = new File(rootDir);
        Files.walkFileTree(f.toPath(), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                for (int i = 0; i < (int)(Math.random() * 3) + 1; i++) {
                    String fileName = dir.toString() + "/"
                            + FILE_PATTERN.replaceAll("#", Integer.toString(i));
                    try(FileOutputStream stream = new FileOutputStream(fileName)) {
                        for (int j = 0; j < (int)(Math.random() * 190) + 10; j++) {
                            stream.write(String.valueOf((int)(Math.random() * 9)).getBytes());
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    File f = new File(fileName);
                    if (f.exists() && f.isFile()) {
                        sortedFiles.add(new AFile(f.getPath(), f.length()));
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void main(String[] args) {
        File rootDir = new File(PATH_PREFIX);
        try {
            createDirs(PATH_PREFIX);
            createFiles(PATH_PREFIX);
            // Вывод результатов
            if (!sortedFiles.isEmpty()) {
                AFile first = sortedFiles.first();
                AFile biggest = sortedFiles.last();
                sortedFiles.removeIf(o -> o.size > first.size);

                Function<File,String> filePrinter = (path) -> {
                    StringBuilder b = new StringBuilder();
                    try (BufferedReader in = new BufferedReader(new FileReader(path.getAbsoluteFile()))) {
                        String s;
                        while ((s = in.readLine()) != null) {
                            b.append(s);
                            b.append('\n');
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return b.toString();
                };

                System.out.println("All created files:");
                recursivePrintFiles(rootDir);
                System.out.println();
                if (!sortedFiles.isEmpty()) {
                    System.out.println("------------------");
                    System.out.println("Smallest file(s)");
                    System.out.println("------------------");
                    for (AFile f : sortedFiles) {
                        System.out.println("File \"" + f.path + "\" " + f.size + " bytes\n<content>");
                        System.out.print(filePrinter.apply(new File(f.path)));
                        System.out.println("</content>");
                    }
                }
                System.out.println("---------------");
                System.out.println("Biggest file");
                System.out.println("---------------");
                System.out.println("File \"" + biggest.path + "\" " + biggest.size + " bytes\n<content length=10>");
                System.out.println(filePrinter.apply(new File(biggest.path)).substring(0, 10));
                System.out.println("</content>");
            }
            // Удаляем все результаты
            recursiveDelete(rootDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
