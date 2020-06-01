package VostrikovaE.Lesson7;

import java.util.Objects;

public class Wolf extends WildAnimal{


    public Wolf(String name) {
        super.setName(name);
    }
    @Override
    public String getFavoriteMeal() {
        return Meal.Wolf.getMeal();
    }


    @Override
    public String toString() {
        return "Wolf{" +
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
        Wolf wolf = (Wolf) obj;
        return this.getName().equals(wolf.getName());
    }
}
