package VostrikovaE.Lesson7;

import java.util.Objects;

public class Dog extends PetAnimal {
    public Dog(String name) {
        super.setName(name);
    }

    @Override
    public String getFavoriteMeal() {
        return Meal.Dog.getMeal();
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='"+ this.getName() + '\'' + '\n'+
                "favorite meal='"+this.getFavoriteMeal() +'\'' + '\n'+
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getClass().getName());
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dog dog = (Dog) obj;
        return this.getName().equals(dog.getName());
    }
}
