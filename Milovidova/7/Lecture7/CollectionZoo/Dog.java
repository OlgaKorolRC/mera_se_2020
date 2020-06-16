package com.company.Lecture7.CollectionZoo;

public class Dog extends PetAnimal{
    private  String favoriteMeal= "Предпочитает клубничный мармелад." ;
    @Override
    public Object getFavoriteMeal() {
        return favoriteMeal;
    }

    public Dog(String name) {
        super(name);
    }

    public Dog() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "Class- Dog. " + getFavoriteMeal() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
