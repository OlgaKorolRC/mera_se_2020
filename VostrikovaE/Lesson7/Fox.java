package VostrikovaE.Lesson7;

import java.util.Objects;

public class
Fox extends WildAnimal{

    public Fox(String name) {
        super.setName(name);
    }

    @Override
    public String getFavoriteMeal() {
        return Meal.Fox.getMeal();
    }

    @Override
    public String toString() {
        return "Fox{" +
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
        Fox fox = (Fox) obj;
        return this.getName().equals(fox.getName());
    }
}
