package com.company.Lecture7.CollectionZoo;

public class Cat extends PetAnimal{
    private  String favoriteMeal= "Уважает ProPlan.";
    @Override
    public Object getFavoriteMeal() {
        return favoriteMeal;
    }

    public Cat(String name) {
       super(name); 
    }
    public Cat() {
        super();
    }


    @Override
    public String toString() {
        return super.toString() + "Class- Cat. " + getFavoriteMeal() + "\n";
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
