package Lecture_6_2;

public class Bycicle {//изобретаем велосипед :)

	//конструкторы
	public Bycicle () {
		this.modelName=""; 
		this.maxSpeed=0;
	}
	public Bycicle (String modelName,int maxSpeed) {
		this.modelName=modelName; 

		this.maxSpeed=maxSpeed;
	
			
	}
	
	
	@Override
    public int hashCode(){
 
        return 10 * modelName.hashCode() + 20456;
    }
	
	@Override
    public boolean equals(Object obj){
         
        if (!(obj instanceof Bycicle)) return false;
 
        Bycicle p = (Bycicle)obj;
        return this.modelName.equals(p.modelName);
    }
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		if (maxSpeed <0 || maxSpeed >50) {
			System.out.println("Max speed is not in range from 0 to 50. operation aborted");
		}
		else
			this.maxSpeed = maxSpeed;
	}
	
	String modelName; 
	int maxSpeed;//(от 5 до 50км\ч)
}
