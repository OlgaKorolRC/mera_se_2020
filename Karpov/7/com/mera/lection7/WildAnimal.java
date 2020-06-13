package com.mera.lection7;

abstract class WildAnimal extends Animal {

    public WildAnimal(String name) {
        super(name);
    }
    public WildAnimal() {
        super();
    }
}

class Wolf extends WildAnimal{
    public Wolf(String name) {
        super(name);
    }

    public Wolf() {
    }

    @Override
    public String getFavoriteMeal(){
        return "lamb";
    }
}

class Fox extends WildAnimal{
    public Fox(String name) {
        super(name);
    }

    public Fox() {
    }

    @Override
    public String getFavoriteMeal(){
        return "chicken";
    }
}

