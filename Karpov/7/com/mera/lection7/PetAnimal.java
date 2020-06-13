package com.mera.lection7;

import java.util.Random;

abstract class PetAnimal extends Animal {

    public PetAnimal(String name) {
        super(name);
    }
    public PetAnimal() {
        super();
    }
}

class Dog extends PetAnimal{

    public Dog(String name){
        super(name);
    }
    public Dog(){
        super();
    }

    @Override
    public String getFavoriteMeal(){
        return "bones";
    }
}

class Cat extends PetAnimal{
    public Cat (String name){
        super (name);
    }
    public Cat (){
        super();
    }

    @Override
    public String getFavoriteMeal(){
        return "fish";
    }
}