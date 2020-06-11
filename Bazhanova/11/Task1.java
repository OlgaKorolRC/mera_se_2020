import java.util.Random;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {

        int maxName = 5;
        int maxAge = 100;

        String[] firstNames = new String[] {
            "Роман",
            "Тимофей",
            "Владимир",
            "Афанасий",
            "Алексей"
        };


        int n = 5;


        Random rnd = new Random();

        List < Person > persons = new ArrayList < > ();



        for (int i = 0; i < n; i++) {
            int name = rnd.nextInt(maxName);
            int age = rnd.nextInt(maxAge);
            persons.add(new Person(firstNames[name], age));
            System.out.printf("Человек №%d - %s\n", i, persons.get(i));
        }

        System.out.println("");

        Collections.sort(persons, (o1, o2) -> o1.getAge() > o2.getAge() ? 1 : (o1.getAge() == o2.getAge()) ? 0 : -1);


        for (int i = 0; i < n; i++) {
            System.out.printf("Человек №%d - %s\n", i, persons.get(i));
        }
        System.out.println("");



        Collections.sort(persons, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        for (int i = 0; i < n; i++) {
            System.out.printf("Человек №%d - %s\n", i, persons.get(i));
        }


    }
}
class Person {

    private String name;
    private int age;



    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return (name + ", " + age);

    }

}
