package VostrikovaE.Lesson8;

public class Person {
    @JSonName(name="firstName")
    private String firstName;
    @JSonName(name="secondName")
    private String secondName;
    @JSonName(name="Age")
    private int age;
    @JSonIgnore
    private String password;
    private double weight;

    public Person(String firstName, String secondName, int age) {
        this.firstName = firstName;
        this.secondName=secondName;
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
