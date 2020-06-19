package Lecture_6_1;

public class Lesson_6_1 {
	public static void main (String Args[]) {

//trying basic functions		
		try {
			MyArrayList<String> myArray = new MyArrayList<String>(2);
			
			
			myArray.add("Object1");
			System.out.println(myArray.get(0));
			myArray.add("Object2");
			System.out.println(myArray.get(1));
			myArray.remove(null);
			System.out.println(myArray.get(0));
			
			System.out.println(myArray.toArray());	
			Object [] stringArray =myArray.toArray() ;
			System.out.println(stringArray[0]);
		}
		catch (MyArrayStoreException Ex) {
			System.out.println(Ex.getMessage());
    	    System.out.println("Wrong index is:"+Ex.getIndex());
		}

}
}
