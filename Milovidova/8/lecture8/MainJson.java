package com.company.lecture8;

public class MainJson {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        PersonJson personJson= new PersonJson("Vasya",12,"qwerty123");
        System.out.println("New object Person \n" + personJson.toString());

        String serializedPersons = Serialize.serialize(personJson);
        System.out.println("Object Person after serialization \n" + serializedPersons);

        Object deserializedPersons = Deserialize.deserialize(serializedPersons, PersonJson.class);
        System.out.println("Object Person after deserialization \n" + deserializedPersons.toString());
    }
}
