package com.mera.lection6;

import java.util.Objects;
import java.util.Random;

public class Bicycle {

    private final String[] modelList = {"Bicycle1", "Bicycle2", "Bicycle3", "Bicycle4"};
    private final String modelName;
    private final int maxSpeed;

    public Bicycle (String model, int speed){
        modelName=model;
        if (speed <= 50){
            maxSpeed = speed;
        }
        else {
            maxSpeed = 50;
        }
    }

    public Bicycle(){
        Random r = new Random();
        modelName=modelList[r.nextInt(4)];
        maxSpeed=r.nextInt(46)+5;
    }

    @Override
    public String toString(){
        return String.format("bicycle %s with max speed %d", modelName, maxSpeed);
    }

    @Override
    public boolean equals(Object sample) {
        if (sample != null && this.getClass()==sample.getClass()){
            Bicycle bicycle = (Bicycle) sample;
            return (bicycle.getModelName()==this.getModelName()&& this.getSpeed()==bicycle.getSpeed());
        }
        else
            return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(modelName, maxSpeed);
    }

    public int getSpeed(){
        return maxSpeed;
    }

    public String getModelName() {
        return modelName;
    }
}
