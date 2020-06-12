package iljicheva.lesson9.task1;

import java.io.IOException;
import java.util.List;

public class Main {
    static final String ROOT_PATH = ".\\SE2020_LESSON9";

    public static void main(String[] args) throws IOException {
        /*String[] createdFile = new String[9];
        String[] createdDirs = new String[4];*/
        Utils.deleteDirectory(ROOT_PATH);

        System.out.println("create tree dirs");
        final String treeDirs  = Utils.createTreeDirs();
        System.out.println(treeDirs);

        System.out.println("create files in each dirs");
        Utils.createFiles();

        System.out.println("find smallest files");
        List smallestFiles = Utils.findSmallestFile();
        for (Object filePath : smallestFiles) {
            System.out.println(filePath);
            Utils.printSizeFile(filePath.toString());
            Utils.printFileContent(filePath.toString());
        }

        System.out.println("find biggest files");
        List biggestFiles = Utils.findBiggestFile();
        for (Object filePath : biggestFiles) {
            System.out.println(filePath);
            Utils.printSizeFile(filePath.toString());
            Utils.printFirstNCharsFromFile(filePath.toString(), new byte[30]);
        }

        Utils.deleteDirectory(ROOT_PATH);
    }
}
