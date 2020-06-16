package com.company.Lecture7.CollectionZoo;

import java.util.Collection;

public abstract class PetAnimal extends Animal {
    public PetAnimal(String name) {
        super(name);
    }
    public PetAnimal() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() ;
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
