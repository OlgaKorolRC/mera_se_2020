package com.company.Lesson11.Lecture9;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {


        Collection <File> allFiles2= new ArrayList<>();
        FilteredAttributes.getAllFiles2("C:\\Users\\smilovid\\Desktop\\java\\Date\\7\\SE2020_LESSON9",allFiles2);

        ArrayList <Long> sizeFiles=new ArrayList<>();
        for(File item : allFiles2)
        {
            System.out.println("\n Элемент коллекции allFiles2 " + item);
            System.out.println("\n Элемент коллекции allFiles2 Размер файла" + item.length());
            sizeFiles.add(item.length());
        }


        Long minSizeFile=  Collections.min(sizeFiles);
        System.out.println("минимальный размер файла" + minSizeFile);

        Long maxSizeFile=  Collections.max(sizeFiles);
        System.out.println("максимальный размер файла" + maxSizeFile);

        /////Выведем содержимое файла с минимальным размером
        FilteredAttributes.readMinSizeFile(allFiles2, minSizeFile);

        /////Выведем 10 первых символов файла с максимальным размером
        FilteredAttributes.readMaxSizeFile10char(allFiles2,maxSizeFile);

    }
}
