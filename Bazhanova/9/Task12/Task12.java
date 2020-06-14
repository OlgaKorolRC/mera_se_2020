import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import myvisitor.*;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.*;

class Task12 {

    private static final String DIR = "SE2020_LESSON9";


    public static void main(String[] args) throws IOException {

        // Task1

        createFolder(DIR);


        createFolders(3, DIR);


        final File folder = new File(DIR);
        List < File > files = new ArrayList < > ();
        processFilesFromFolder(folder, files);

        //сортируем файлы по возрастанию размера
        Collections.sort(files, (o1, o2) -> o1.length() > o2.length() ? 1 : (o1.length() == o2.length()) ? 0 : -1);


        System.out.println("\n");


        for (File entry: files) {
            System.out.println("file name=  " + entry.getParent() + "/" + entry.getName() + " size = " + entry.length());
        }

        // end Task1

        // Task2

        System.out.println("\n");
        searchMinMaxFile(files);
        System.out.println("\n");

        deleteDir(DIR);
        // end Task2
    }




    //создать папку
    public static void createFolder(String name) {
        final File dir = new File(name);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Kaтaлoг " + dir.getAbsolutePath() + " ycпeшнo coздaн.");
            } else {
                System.out.println("Kaтaлoг " + dir.getAbsolutePath() + " coздать нe yдaлocь.");
            }
        } else {
            System.out.println(" Kaтaлoг " + dir.getAbsolutePath() + " yжe cyщecтвyeт.");
        }
    }

    //создать вложенные папки с файлами
    public static void createFolders(int n, String dir0) {
        String name = dir0;

        Random rnd = new Random();

        int numFiles = 3;
        createFiles(rnd.nextInt(numFiles) + 1, name);

        for (int i = 0; i < n; i++) {
            name = name + "//Directory_" + (i + 1);
            createFolder(name);
            createFiles(rnd.nextInt(numFiles) + 1, name);
            System.out.println(" name=  " + name);

        }
    }

    //создать 1 файл со случайной строкой
    public static void createFile(String name) {

        // определяем объект для каталога
        File myFile = new File(name);
        // System.out.println("File name: " + myFile.getName());
        // System.out.println("Parent folder: " + myFile.getParent());
        if (myFile.exists())
            System.out.println("File exists");
        else {
            System.out.println("File not found");


            // создадим новый файл
            File newFile = new File(name);
            try {
                boolean created = newFile.createNewFile();
                if (created)
                    System.out.println("File " + myFile.getParent() + "/" + myFile.getName() + " has been created");
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }

            try (FileWriter writer = new FileWriter(name, false)) {
                // запись всей строки
                String text = randNumbersText();
                writer.write(text);
                // запись по символам
                writer.append('\n');

                writer.flush();
                writer.close();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    //создать n файлов со случайной строкой
    public static void createFiles(int n, String dir0) {

        for (int i = 0; i < n; i++) {
            String name = dir0 + "//File_" + (i + 1) + ".txt";
            createFile(name);
            System.out.println(" name=  " + name);

        }
    }



    //сгенерировать рандомный текст
    public static String randNumbersText() {
        Random rnd = new Random();

        String str = "";
        int min = 10, max = 200;
        int n = min + rnd.nextInt(max - min + 1);


        for (int i = 0; i < n; i++) {
            int k = rnd.nextInt(10);
            str = str + k;
        }
        return str;
    }



    public static void printPath(String dir) throws IOException {
        Files.walkFileTree(Paths.get(dir), new MyFileVisitor());

    }


    public static void searchMinMaxFile(List < File > files) {

        long max = files.get(files.size() - 1).length();
        int i = 1;

        System.out.println("файлы с максимальным размером: ");

        while (files.get(files.size() - i).length() >= max) {

            System.out.println(files.get(files.size() - i).getAbsolutePath() + " size= " + files.get(files.size() - i).length());


            readFile(DIR + "/Directory_1/File_1.txt", 10);
            System.out.println("\n");

            i++;
        }



        //////////////////

        long min = files.get(0).length();
        int j = 0;

        System.out.println("файлы с минимальным размером: ");

        while (files.get(j).length() <= min) {

            System.out.println(files.get(j).getAbsolutePath() + " size= " + files.get(j).length());
            j++;
        }


    }

    public static void processFilesFromFolder(File folder, List < File > files) {
        File[] folderEntries = folder.listFiles();

        HashMap < Long, String > map = new HashMap < > ();


        for (File entry: folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry, files);
                continue;
            } else {
                // System.out.println(" file name=  " + entry.getParent()+"/"+entry.getName());
                files.add(entry);
                map.put(entry.length(), entry.getParent() + "/" + entry.getName());

            }
        }



    }

    public static void readFile(String name, int n) {
        System.out.println("Чтение первых " + n + " символов файла\n" + name);
        File file = new File(name);

        try (FileReader fr = new FileReader(file)) // Создание объекта FileReader
        {
            char[] a = new char[n]; // Количество символов, которое будем считывать
            fr.read(a); // Чтение содержимого в массив

            for (char c: a)
                System.out.print(c); // Вывод символов один за другими
            fr.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public static void deleteDir(String folder) {
        // удаляем файл рекурсивно
        recursiveDelete(new File(folder));
    }

    public static void recursiveDelete(File file) {
        // до конца рекурсивного цикла
        if (!file.exists())
            return;

        //если это папка, то идем внутрь этой папки и вызываем рекурсивное удаление всего, что там есть
        if (file.isDirectory()) {
            for (File f: file.listFiles()) {
                // рекурсивный вызов
                recursiveDelete(f);
            }
        }
        // вызываем метод delete() для удаления файлов и пустых(!) папок
        file.delete();
        System.out.println("Удалено: " + file.getAbsolutePath());
    }





}
