package bicycle;
import java.util.Objects;


public class Bicycle {
    private String modelName;
    private int maxSpeed; // (от 5 до 50км\ч);
    private final int MAX_SPEED = 50;
    private final int MIN_SPEED = 5;

    
    public Bicycle(String modelName, int maxSpeed) {

        this.modelName = modelName;

        if (maxSpeed > MAX_SPEED)
            throw new IllegalArgumentException("максимальная скорость не может быть больше "+ MAX_SPEED);
        else if (maxSpeed < MIN_SPEED)
            throw new IllegalArgumentException("максимальная скорость не может быть меньше "+ MIN_SPEED);
        else
            this.maxSpeed = maxSpeed;

    }

    public Bicycle(String modelName) {
        this.modelName = modelName;
        this.maxSpeed = MIN_SPEED;
    }

    public int getSpeed() {

        return this.maxSpeed;
    }
    
    @Override
    public String toString() {
        return (modelName + ", maxSpeed = " + maxSpeed);
    }
    
      @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Bicycle)) {
            return false;
        }

        Bicycle bicycle = (Bicycle) o;

        return bicycle.modelName.equals(modelName) &&
            bicycle.maxSpeed == maxSpeed;
    }


    @Override
    public int hashCode() {
        return Objects.hash(modelName, maxSpeed);
    }
}
