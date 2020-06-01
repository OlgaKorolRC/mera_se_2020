package VostrikovaE.Lesson7;

public enum Meal {
    Wolf("Cow"),
    Cat("Fish"),
    Dog("Bone"),
    Fox("Chicken");

    private String meal;
     Meal(String meal){
        this.meal=meal;
    }

    public String getMeal() {
        return meal;
    }
}
