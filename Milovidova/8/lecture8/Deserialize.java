package com.company.lecture8;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class Deserialize {
    public static Object deserialize(String serializableString, Class serializableClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        serializableString = serializableString.replaceAll("[{}\"\n]", "");
        String[] objectFields = serializableString.split(",");
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < objectFields.length; i++) {
            int splitIndex = objectFields[i].indexOf(":");
            String key = objectFields[i].substring(0, splitIndex);
            String value = objectFields[i].substring(splitIndex + 1);
            map.put(key, value);
        }

        Field[] fields = serializableClass.getDeclaredFields();
        Object deserialazeResult = serializableClass.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonName.class)) {

                String key = field.getAnnotation(JsonName.class).value();

                String value = map.get(key);
                if (value != null) {
                    if (field.getType() == String.class) {

                        field.set(deserialazeResult, value);

                    } else if (field.getType() == double.class) {

                        field.set(deserialazeResult, Double.parseDouble(value));

                    }
                }
            }
        }
        return deserialazeResult;
    }
}

