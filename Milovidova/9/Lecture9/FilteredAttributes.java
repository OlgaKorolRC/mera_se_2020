package com.company.Lesson11.Lecture9;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class FilteredAttributes {

    public static void getAllFiles2(String paph, Collection<File> allFiles){
        File dir = new File(paph);
        // Collection <File> allFiles= new ArrayList<>();
        if(dir.isDirectory()) {
            for (File item : dir.listFiles()) {

                if (item.isDirectory()) {

                    getAllFiles2(item.getAbsolutePath(),allFiles);

                } else {

                    allFiles.add(item);
                }
            }
        }
        //return allFiles;
    }

    public static void readMinSizeFile(Collection<File> allFiles2, Long minSizeFile) {

        for(File item : allFiles2)
        {
            if(item.length()==minSizeFile){
                System.out.println("\n Файл с минимальной длиной " +item.getName() + item.length());
                try(FileReader reader = new FileReader(item))
                {
                    int c;
                    while((c=reader.read())!=-1){

                        System.out.print((char)c);
                    }
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public static void readMaxSizeFile10char(Collection<File> allFiles2, Long maxSizeFile) {
        for(File item : allFiles2) {
            if (item.length() == maxSizeFile) {
                System.out.printf("\n Файл с максимальной длиной %s  %s  \n", item.length(), item.getAbsolutePath() );

                try(FileReader reader = new FileReader(item))
                {
                    char[] buf = new char[256];
                    int c;
                    while((c = reader.read(buf))>0){

                        if(c < 256){
                            buf = Arrays.copyOf(buf, c);
                        }

                    }
                    for(int i=0; i<10;i++) {
                        System.out.print(buf[i]);
                    }
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            }

        }
    }


}
