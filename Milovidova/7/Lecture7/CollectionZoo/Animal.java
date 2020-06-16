package com.company.Lecture7.CollectionZoo;

import java.util.Objects;

public abstract class Animal {
    private String name;
    public  abstract <T> T getFavoriteMeal();


    public Animal(String name) {
        this.name = name;
    }
    public Animal() {
        this.name = "";
    }

    @Override
    public String toString() {
        return "Name- " + name + ". " ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getClass());
    }
}
