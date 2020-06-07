package com.company.Lecture7.CollectionZoo;

public class Wolf extends WildAnimal{
    private  String favoriteMeal= "На спортпите.";
    @Override
    public Object getFavoriteMeal() {
        return favoriteMeal;
    }

    public Wolf(String name) {
        super(name);
    }

    public Wolf() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "Class- Wolf. "+ getFavoriteMeal() + "\n";
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
