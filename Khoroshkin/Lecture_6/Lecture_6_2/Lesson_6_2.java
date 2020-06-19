package Lecture_6_2;
import java.util.Random;

import Lecture_6.MyArrayList;
import Lecture_6.MyArrayStoreException;
public class Lesson_6_2 {
	public static void main (String Args[]) {

//Trying with the bycicles

		try {
			System.out.println("Start with the Bycicles");
			MyArrayList<Bycicle> byciclies = new MyArrayList<Bycicle>(10);
			Random rnd=new Random();
			int tmp;
			for (int i=0;i<10;i++) {
				byciclies.add(new Bycicle(BycicleModel.getCode(rnd.nextInt(4)+1),rnd.nextInt(50)));
				System.out.println("Bycicle with model "+byciclies.get(i).modelName+" and max speed "+byciclies.get(i).maxSpeed+" has been created");
			}
			//System.out.println("SSSSSie is"+byciclies.size());

		
			tmp=rnd.nextInt(50);
			System.out.println("Bycicles with speed less than "+tmp+" will be removed");
			
			for(int i=0; i<byciclies.size();i++) {
				//System.out.println("Iteration for index "+i);
				if (byciclies.get(i).getMaxSpeed()<tmp) {
					System.out.println("\nremoving instance "+i+"with MOD "+byciclies.get(i).getModelName()+"with the MAX_SPEED "+byciclies.get(i).getMaxSpeed());
					//System.out.println("index before removal "+i);
					byciclies.remove(byciclies.get(i));
				    i--;
				    //System.out.println("index after removal "+i);
				    //System.out.println("Size become to "+byciclies.size());
				}
			}
			System.out.println("Bycicles collection is:");
			//System.out.println("SSSSSie2 is"+byciclies.size());
			for(int i=0; i<byciclies.size();i++) {
				if (byciclies.get(i)!=null) {
					System.out.println(byciclies.get(i).getModelName()+" : "+byciclies.get(i).getMaxSpeed());
				    System.out.println("i="+i);
				}
				else
					System.out.println("instance "+i+" empty");
			}

			
		}
		catch (MyArrayStoreException Ex) {
			System.out.println(Ex.getMessage());
    	    System.out.println("Wrong index is:"+Ex.getIndex());
		}

//trying with the digits
		/*try {
			MyArrayList<Integer> myDigitArray = new MyArrayList<Integer>(10);
			Random rnd=new Random();
			for(int i=0;i<myDigitArray.getMaxOfIndexes();i++) {
				myDigitArray.add(rnd.nextInt(100));
				System.out.println(myDigitArray.get(i));
			}
			
			for(int i=0; i<myDigitArray.size();i++) {
				if (myDigitArray.get(i)%2!=0) {
					System.out.println("\nremoving instance "+i+"Value is "+myDigitArray.get(i));		
					myDigitArray.remove(myDigitArray.get(i));
				    i--;
				    
				}
			}
			for(int i=0; i<myDigitArray.size();i++) {
				System.out.println(myDigitArray.get(i));
			}
			
			
		}
		
		catch (MyArrayStoreException Ex) {
			System.out.println(Ex.getMessage());
    	    System.out.println("Wrong index is:"+Ex.getIndex());
		}
		System.out.println("End");
	 
		
	}*/
}
}
