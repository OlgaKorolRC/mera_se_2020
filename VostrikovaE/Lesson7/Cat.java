package VostrikovaE.Lesson7;

import java.util.Objects;

public class Cat extends PetAnimal{

    public Cat(String name) {
        super.setName(name);
    }


    @Override
    public String getFavoriteMeal() {
        return Meal.Cat.getMeal();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='"+ this.getName() + '\'' + '\n'+
                "favorite meal='"+this.getFavoriteMeal() +'\'' + '\n'+
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cat cat = (Cat) obj;
        return this.getName().equals(cat.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getClass().getName());
    }
}
