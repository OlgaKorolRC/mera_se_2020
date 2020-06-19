package Lecture_6_1;

@SuppressWarnings("serial")
public class MyArrayStoreException extends Exception {
	
	private int index;
    public int getIndex(){return index;}
    
    public MyArrayStoreException(String message, int index){
    	 super(message);
        this.index=index;
    }
}
