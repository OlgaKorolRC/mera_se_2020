package VostrikovaE.Lesson8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainLesson8 {

    public static void main(String[] args) {
        Person[] persons = {new Person("Вася", "Пушкин", 15), new Person("Толя", "Колотушкин", 12)};
        persons[0].setWeight(12.1);
        persons[0].setPassword("12345678");
        persons[1].setWeight(34.5);
        persons[1].setPassword("qwerty");
        String resultJson;
        for (Person person : persons) {
            try {
                resultJson = ReflectionPerson.getJson(person);
                System.out.println(resultJson);
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
        }


    }





}