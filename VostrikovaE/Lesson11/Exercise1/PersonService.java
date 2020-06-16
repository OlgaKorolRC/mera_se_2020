package VostrikovaE.Lesson11.Exercise1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    private final List<Person> personList = new ArrayList<>();


    public void addPerson(Person person){
        personList.add(person);
    }

    public List<Person> sortByName(){
       List<Person> sortList= personList.stream()
                                        .sorted(Comparator.comparing(Person::getName))
                                        .collect(Collectors.toList());
        return sortList;
    }

    public List<Person>  sortByAge(){
        List<Person> sortList= personList.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
        return sortList;
    }

}
