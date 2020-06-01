package VostrikovaE.Lesson8;

import java.lang.reflect.Field;

public class ReflectionPerson {
    /*
    * reflectObject - экземлпяр объекта который преводим в Json
    * clazz - класс объекта для того, чтобы достать аннотированные поля
    * */
    public static String getJson(Object reflectObject) throws IllegalAccessException {
        Field[] fileds=reflectObject.getClass().getDeclaredFields();
        String Json="";
        String fieldType="";
        Json=Json+reflectObject.getClass().getSimpleName()+"{"+'\n'; // добавляем имя класса как идентификатор объекта
        for(Field field: fileds){
            field.setAccessible(true);
            if (field.isAnnotationPresent(JSonName.class)) { //поле снабжено аннотацией JsonName
                try {
                    fieldType = field.get(reflectObject).getClass().getName(); //получаем типа поля, если java.lang.String то поле надо взять в кавычки
                    switch (fieldType) {
                        case "java.lang.String" -> Json = Json + '\t' + field.getAnnotation(JSonName.class).name() + " : \"" + field.get(reflectObject) + "\"," + '\n';
                        default -> Json = Json + '\t' + field.getAnnotation(JSonName.class).name() + " : " + field.get(reflectObject) + "," + '\n';
                    }
                } catch (NullPointerException ex) {
                    Json = Json + '\t' + field.getAnnotation(JSonName.class).name() + " : null," + '\n';
                }
            }
            if (field.isAnnotationPresent(JSonIgnore.class)){ //поле снабжено аннотацией JSonIgnore
                Json=Json+'\t'+field.getName()+" : null,"+'\n';
            }
        }
        Json=Json+"}"+'\n';
        return Json;
    }
}
