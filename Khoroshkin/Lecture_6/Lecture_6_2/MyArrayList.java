package Lecture_6_2;

import Lecture_6.MyArrayStoreException;

public class MyArrayList<T> {
	
	
	    public MyArrayList() throws MyArrayStoreException{
	    	this.maxOfIndexes=10;
	    	this.myArray = new Object[maxOfIndexes];
        }
	    public MyArrayList(int index) throws MyArrayStoreException{
	    	this.maxOfIndexes=index;
	      	if (index<=0 || index>maxOfIndexes) throw new MyArrayStoreException("Counts of items can't be more than "+maxOfIndexes+
                    " your tried to create "+index+
                    " indexes, Object hasn't been created ",index);
	      	this.myArray = new Object[index];	  	
	    }
//methods
	    public T get(int index) throws MyArrayStoreException{//взять элемент по индексу.
	    	if (index>this.index || index <0) throw new MyArrayStoreException("It's not possible to take this index", index);
	    	@SuppressWarnings("unchecked")
	    	final T e = (T) myArray[index];
		    return e;
		       	
	    }
	     
	    
	    public void add(T newItem) throws MyArrayStoreException{//добавляет элемент  в коллекцию
	    	//System.out.println("Started with index:"+this.index+" max is"+this.maxOfIndexes);
	    	if (newItem==null) throw new MyArrayStoreException("You trying to add null object. Aborted",this.index);
	    	index++;
	    	
	    	if (this.index<0 || this.index>(this.maxOfIndexes-1)) throw new MyArrayStoreException("It's not possible to create more than"+this.maxOfIndexes+"  objects",this.index);
	        this.myArray[this.index]=newItem;
	        
	    }
	    
	    public boolean remove(T object) throws MyArrayStoreException{ //возвращает true, если элемент был найден и удален
	    	System.out.println("Starting removal");
	    	if (object==null) throw new MyArrayStoreException("You trying to delete null object. Aborted",0);
	    	if (this.index<0) throw new MyArrayStoreException("There are no object in collection. Aborted",0);
	  
	    	for (int i=0;i<=this.index;i++){ 
	    		
	    		if (object==this.myArray[i]) { 
	            	System.out.println("Object "+object.toString()+" found and will be removed, index="+i+"size is "+this.size());
	            	//this.myArray[i]=this.myArray[this.index];
	                System.out.println("Note: all elements after element with index"+i+" will be moved to the left");
	            	
	            	for (int j=i;j<this.index;j++) {
	                	this.myArray[j]=this.myArray[j+1];
	                }
	            	index--;
	            	return true;
	            }
	    		
	    	    
	    	}
	    	return false;
	    }
	    
	    public int getMaxOfIndexes() {
			return maxOfIndexes;
		}
	
		public T[] toArray() {//возвращает массив из всех текущих элементов
	    	@SuppressWarnings("unchecked")
	    	T[] tmp=(T[]) this.myArray;
	    	return tmp;
	    	  	
	    }
	    public int size() {
	    	return index+1;
	    }
//поля
	private int index=-1;
    private int maxOfIndexes=0;
	private Object[] myArray;

}
