package VostrikovaE.Lesson8;

import java.lang.reflect.InvocationTargetException;

public class MainLesson8 {

    public static void main(String[] args) throws IllegalAccessException, JsonDesirializeException {
        Object deserializePerson=null;

        Person person=new Person("Коля","Пушкин",12);
        person.setPassword("qwerty");
        person.setWeight(12.3);

        String personJson= ReflectionPerson.getJson(person);
        System.out.println(personJson);
        JsonDesirializer jsonDesirializer = JsonDesirializer.newInstance(Person.class);

        try {
           deserializePerson=jsonDesirializer.deserialize(personJson);
            System.out.println(deserializePerson.toString());
        } catch (IllegalAccessException|InstantiationException|InvocationTargetException e){
            System.out.println("Проблема с reflection API "+ e);
        } catch (JsonDesirializeException e){
            System.out.println(e.getMessage());
        }

    }


}