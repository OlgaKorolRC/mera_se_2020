package VostrikovaE.Lesson9;

import VostrikovaE.Lesson9.Exercise1.FileCreator;
import VostrikovaE.Lesson9.Exercise2.FileAnalyzer;

public class MainLesson9 {

    public static void main(String[] args)  {


        String pathToSE2020="D:\\SE2020_Lesson9";
        FileCreator fileCreator=FileCreator.newInstance(1,5, 10,200, pathToSE2020);
        fileCreator.clearAllFiles();
        fileCreator.createFolders(3); //создали папки
        fileCreator.createRandomFiles(); //создали файлы в папках
        System.out.println("Были созданы следующие файлы: ");
        fileCreator.printAllFilePath();

        FileAnalyzer fileAnalyzer = new FileAnalyzer(fileCreator);
        fileAnalyzer.printSmallFile();
        fileAnalyzer.printBigFile( 10);

        fileCreator.clearAll();

    }
}
