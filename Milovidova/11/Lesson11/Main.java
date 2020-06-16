package com.company.Lesson11;
import java.util.*;

public class Main {
    public static void main(String[] arrgs) {
       List <Person> people=new ArrayList<>();

        people.add(new Person("Вася",24));
        people.add(new Person("Дмитрий",20));
        people.add(new Person("Карл",18));
        people.add(new Person("Антон",29));
        System.out.println("Коллекция до сортировки \n" + people.toString());




        Collections.sort(
                people,
                Comparator.comparing(Person::getName)
        );
        System.out.println("Сортировка по имени \n" + people.toString());





        Collections.sort(
                people,
                Comparator.comparing(Person::getAge)
        );
        System.out.println("Сортировка по возрасту \n" + people.toString());





       /* //Черновик
        Collections.sort(
                people,
                new Comparator<Person>() {
                    public int compare(Person p1, Person p2){
                        return p1.getName().compareTo(p2.getName());
                    }
                }
        );
        System.out.println(people.toString());*/
        /*//Черновик (рабочий пример)
        people.sort((o1, o2) -> o1.getAge() - o2.getAge());

        System.out.println("\nСписок после сортировки:");
        for (Person person : people){
            System.out.printf("%s: %d\n", person.getName(), person.getAge());
        }*/

    }
}
