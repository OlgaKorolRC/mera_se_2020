package com.mera.gokhmak.les11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        // Заполняем
        for (String name : new String[]{"Анна","Глеб","Михаил","Мария","Петр","Глеб","Павел","Егор","Анна","Петр","Егор",
                                            "Мария","Дмитрий","Григорий"})
        {
            personList.add(new Person(name, (int)(Math.random() * 63)));
        }
        // Сортируем
        Collections.sort(personList, (o1, o2) -> o2.getName().compareTo(o1.getName()));
        Collections.reverse(personList);
        System.out.println("----------------------------");
        System.out.println("Сортировка по имени");
        System.out.println("----------------------------");
        for (Person person : personList) {
            System.out.println(person);
        }
        // Сортируем
        Collections.sort(personList, (o1, o2) -> o2.getAge().compareTo(o1.getAge()));
        Collections.reverse(personList);
        System.out.println("----------------------------");
        System.out.println("Затем сортировка по возрасту");
        System.out.println("----------------------------");
        for (Person person : personList) {
            System.out.println(person);
        }
    }
}

class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
