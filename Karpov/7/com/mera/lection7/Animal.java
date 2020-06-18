package com.mera.lection7;

import java.util.Objects;
import java.util.Random;

abstract class Animal {
    String[] nameList = {
            "Рыжик",
            "Прожорка",
            "Умка",
            "Толстячок",
            "Хмырь",
            "Растяпа",
            "Дубина"
    };

    String name;

    public Animal(String name){
        this.name=name;
    }

    public Animal(){
        name=nameList[new Random().nextInt(nameList.length)];
    }
    abstract String getFavoriteMeal();

    @Override
    public boolean equals (Object o){
        if (this.getClass() == o.getClass()){
            Animal animal = (Animal) o;
            return animal.name.equals(this.name);

        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }



    @Override
    public String toString() {
        return String.format("It is %s %s, favorite meal: %s", getClass().getSimpleName(), name, getFavoriteMeal());
    }

}


