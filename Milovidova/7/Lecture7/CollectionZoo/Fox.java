package com.company.Lecture7.CollectionZoo;

public class Fox extends WildAnimal{
    private  String favoriteMeal= "Никогда не откажется от Чикен Макнаггетс.";
    @Override
    public Object getFavoriteMeal() {
        return favoriteMeal;
    }

    public Fox(String name) {
        super(name);
    }

    public Fox() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "Class- Fox. " + getFavoriteMeal() + "\n";
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
