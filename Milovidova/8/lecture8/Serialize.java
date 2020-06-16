package com.company.lecture8;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Serialize {

    public static String serialize(Object serializeObject) throws IllegalAccessException {

        Class<?> serializeObjectClass = serializeObject.getClass();

        ArrayList<Field> fields = new ArrayList<>();

        for (Field field : serializeObjectClass.getDeclaredFields()) {

            if (field.getAnnotation(JsonIgnore.class) == null) {
                if((field.getType()==String.class)||(field.getType()==double.class)) {
                    fields.add(field);
                }

            }

        }

        StringBuilder serializeResult = new StringBuilder();

        for (int i = 0; i < fields.size(); i++) {

            {

                Field field = fields.get(i);

                field.setAccessible(true);

                String jsonName = field.getAnnotation(JsonName.class).value();
                if (field.getType() == String.class) {

                    serializeResult.append("\"").append(jsonName).append("\":\"").append(field.get(serializeObject)).append("\"");
                }
                else if (field.getType() == double.class) {

                    serializeResult.append("\"").append(jsonName).append("\":").append(field.get(serializeObject));

                }

                if (i != fields.size() - 1) {

                    serializeResult.append(",\n");
                }
            }
        }

        return "{\n" + serializeResult + "\n}";

    }
}

