import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MySerializable {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface JsonName {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface JsonIgnore {}


class Main {
    @MySerializable("Person")
    static class Person {

        @JsonName("name")
        public String firstName;


        @JsonName("lastname")
        public String lastName;

        @JsonName("years")
        public double age;

        @JsonIgnore
        public String password;

        public Person(String firstName, String lastName, int age, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.password = password;
        }

        public Person() {}

        @Override
        public String toString() {
            return "Person{" +
                "firstName='" + firstName + "\', " +
                "lastName='" + lastName + "\', " +
                "age=" + age + ", " +
                "password='" + password + '\'' +
                '}';
        }
    }

    static class Serializer {
        static public String serialize(Object object) throws IllegalAccessException {
            StringBuilder stringBuilder = new StringBuilder();

            for (Annotation headAnnotation: object.getClass().getAnnotations()) {
                if (headAnnotation instanceof MySerializable) {
                    stringBuilder.append("{\n");

                    for (Field field: object.getClass().getFields()) {
                        final JsonName fieldAnnotation = field.getAnnotation(JsonName.class);
                        if (fieldAnnotation != null) {

                            stringBuilder.append('\"' + fieldAnnotation.value());
                            if (field.getType() == String.class) {
                                stringBuilder.append('\"' + ": \"" + field.get(object) + "\",\n");
                            } else if (field.getType() == double.class) {
                                stringBuilder.append('\"' + ": " + field.get(object) + ",\n");
                            }

                        }
                    }
                    // удалить лишнюю запятую
                    stringBuilder.deleteCharAt(stringBuilder.length() - 2);

                    stringBuilder.append("}");
                }
            }

            return stringBuilder.toString();
        }
    }



    static class Deserializer {

        static public <T> T deserialize(String init, Class <?> objClass)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            Object object = objClass.getConstructor().newInstance();

            init = init.replaceAll("\"", "");

            for (String row: init.split("\n")) {
                for (Field field: objClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    final JsonName annotation = field.getAnnotation(JsonName.class);
                    if (annotation != null) {

                        if (row.contains(": ")) {
                            String firstWord = row.substring(0, row.indexOf(": "));

                            if (firstWord.equals(annotation.value())) {

                                String val = row.substring(row.indexOf(':')).replaceAll("[ ,:\"]", "");

                                if (field.getAnnotatedType().getType() == String.class) {
                                    field.set(object, val);
                                } else if (field.getAnnotatedType().getType() == double.class) {
                                    field.set(object, Double.valueOf(val));
                                }
                            }

                        }
                    }
                }
            }

            return (T) object;
        }
    }

    public static void main(String[] args) {


        Person man = new Person("Vasya", "Vasilkov", 25, "password");

        System.out.println("man =\n" + man + "\n");


        try {
            String s1 = Serializer.serialize(man);
            System.out.println("serialized =\n" + s1);


            System.out.println("\n");

            Person d1 = Deserializer.deserialize(s1, Person.class);
            System.out.println("deserialized =\n" + d1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
