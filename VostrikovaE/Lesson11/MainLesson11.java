package VostrikovaE.Lesson11;

import VostrikovaE.Lesson11.Exercise1.Person;
import VostrikovaE.Lesson11.Exercise1.PersonService;
import VostrikovaE.Lesson11.Exercise2.Calculator;

import java.util.List;

public class MainLesson11 {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {

        PersonService personService=new PersonService();

        personService.addPerson(new Person("Alex", 12));
        personService.addPerson(new Person("Федор", 56));
        personService.addPerson(new Person("Garry", 54));
        personService.addPerson(new Person("Rick", 33));
        personService.addPerson(new Person("Bob", 8));
        personService.addPerson(new Person("Валентин", 12));
        personService.addPerson(new Person("Will", 44));
        personService.addPerson(new Person("Amanda", 89));

        List<Person> sortByAge = personService.sortByAge();
        List<Person> sortByName = personService.sortByName();

        System.out.println(ANSI_RED+"Задание 1 "+'\n' + ANSI_RESET);

        System.out.println("Сортировка по возрасту");
        sortByAge.forEach(System.out::println);
        System.out.println('\n'+"Сортировка по имени");
        sortByName.forEach(System.out::println);

        // --------------------- Задание 2

        System.out.println('\n'+ANSI_RED+"Задание 2 "+'\n' + ANSI_RESET);

        Calculator calculator = new Calculator();

        calculator.addOperation("sum", (a,b)->a+b);
        calculator.addOperation("div", (a,b)->a/b);
        calculator.addOperation("mul", (a,b)->a*b);
        calculator.addOperation("neg", (a,b)->a-b);
        calculator.addOperation("exp", (a,b)->Math.pow(a, b));
        calculator.addOperation("root", (a,b)->Math.pow(a, 1/b));

            System.out.println("4+5="+calculator.calc("sum", 4.0, 5.0));
            System.out.println("4/5="+calculator.calc("div", 4.0, 5.0));
            System.out.println("4*5="+calculator.calc("mul", 4.0, 5.0));
            System.out.println("4-5="+calculator.calc("neg", 4.0, 5.0));
            System.out.println("4^5="+calculator.calc("exp", 4.0, 5.0));
            System.out.println("4^(1/5)="+calculator.calc("root", 4.0, 5.0));



    }
}
