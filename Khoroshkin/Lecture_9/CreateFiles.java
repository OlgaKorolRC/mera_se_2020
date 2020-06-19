package Lecture_9;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateFiles {
	
	private static final int CHAR_MAX = 200;
    private static final int CHAR_MIN = 10;	
    private static final int CHAR_ALL = 10;	
    
    static FileWriter fileWriter;
    
	public static void main(String[] args) throws IOException {
		Random random = new Random();
		int numofFiles = random.nextInt(2)+1;;
		String path = "C:\\Users\\ykhorosh\\Desktop\\SE2020_LESSON9";
		File dir = new File(path);
		boolean created = dir.mkdir();
        if(created) {
            System.out.println("Folder has been created");
        	fileCreate(path, numofFiles);  
	}
	

        int numFolders = random.nextInt(2)+1;
        System.out.println("Number of folder" + numFolders);
        
        for(int i=1; i<=numFolders; i++){
            path+= "\\" + i;
            dir = new File(path);
            created = dir.mkdir();
            if(created) {
                System.out.println("Folder has been created" + " " + path);           	
            	fileCreate(path, numofFiles);

        }
    }
	}      
        
    public static void fileCreate(String path, int numofFiles) throws IOException {
    	
    	for(int i=1; i<=numofFiles; i++){
    	    String filePath= path + "\\File_" + i + ".txt";
            System.out.println("filePath" + filePath);
            fileWriter = new FileWriter(filePath);
            writeCharFile(filePath);
    	}
    }
    	

    public static void writeCharFile(String filePath) throws IOException {
        Random random = new Random();
        try (FileOutputStream fos = new FileOutputStream(filePath)){
            int numChar = random.nextInt(CHAR_MAX - CHAR_MIN) + CHAR_MIN;
            for (int i=0; i<numChar;i++){
                fos.write(String.valueOf(random.nextInt(CHAR_ALL)).getBytes());
            }
        }
        
        
        
        
  }
}