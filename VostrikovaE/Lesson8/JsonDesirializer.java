package VostrikovaE.Lesson8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class JsonDesirializer {
    private String jsonAnalyze;
    private final Class<?> templateClass; // класс десериализуемного объекта

    private JsonDesirializer(Class<?> clazz){
        templateClass=clazz;
    }

    public static JsonDesirializer newInstance(Class<?> clazz) throws JsonDesirializeException {
        // абстрактный класс или интерфейс отбрасываем
    if (Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers())) {
        throw new JsonDesirializeException("Не подходящий типа класса");
    }
        return new JsonDesirializer(clazz);
    }

    public Object deserialize(String json) throws IllegalAccessException, InvocationTargetException, InstantiationException, JsonDesirializeException {
        Object clearObject =null;

        jsonAnalyze=json;
        //регулярное выражение для проверки корректности json
        /*Строка начинается с как минимум 1 буквы/цифры/подчеркивания потом идет {, потом что угодно или \n или \t сколько угодно раз
        * потом идет } и потом снова что угодно или \n или \t сколько угодно раз
        * по факту проверяем что полученные данные соответсвуют формату
        *
        * CheckedContent {
        *       unchecked content
        * }
        * unchecked content
        *
        *
         * */
        String isJsonRegEx="^\\w+\\{(.|\n|\t)*\\}(.|\n|\t)*";
       boolean isJsonObject=jsonAnalyze.matches(isJsonRegEx); //проверяем что получили что то похожее на Json
        if (isJsonObject){ // начинаем разбирать
            int startObjectDesc=jsonAnalyze.indexOf("{"); //индекс с которого начинается описание объекта
            int endObjectDes=jsonAnalyze.indexOf("}"); // индекс с которого заканчивается описание объекта
            String ObjectType=jsonAnalyze.substring(0, startObjectDesc); //получили тип объекта
            String ObjectFields=jsonAnalyze.substring(startObjectDesc+1,endObjectDes-1); //получили само описание объекта
           if (ObjectType.equals(templateClass.getSimpleName())) { // если получили Json именно с тем классом, который нам задали для разбора
             //начинаем вытаскивать все поля из Json
              Map<String, String> fieldsMap =new HashMap<>(); // карта описание поля - его значение
              String[] fields;
              String clearObjectFields= ObjectFields.replaceFirst("\n\t","");// заменяем первый \n\t на пустоту, чтобы дальше нормально разобрать
                 for(String pair: clearObjectFields.split("\n\t")){
                    fields=pair.split(" : ");
                     if (fields.length==2) {
                            fieldsMap.put(fields[0], fields[1].replaceFirst(",","")); //удалим запятую в конце
                     }
                 }
                 // перебираем карту полей значений и записываем в объект
                   Constructor<?>[] constructors=templateClass.getDeclaredConstructors();
                   Constructor<?> workedConstructor=null;
                   for(Constructor<?> constructor: constructors){
                      if (constructor.getGenericParameterTypes().length==0) {
                          //есть конструктор без параметров будем работать с ним
                          workedConstructor=constructor;
                      }
                   }
                 if (workedConstructor!=null){ //есть конструктор без параметров
                     clearObject = workedConstructor.newInstance(); //создали объект
                       for (Map.Entry<String, String> entry: fieldsMap.entrySet()) {
                          String fieldName = entry.getKey();
                          String fieldValue = entry.getValue();
                           setFieldValue(clearObject, fieldName, fieldValue);
                       }
                    } else {
                     throw new JsonDesirializeException("Нет открытых конструкторов без параметров");
                 }
           } else {throw  new JsonDesirializeException("Класс объекта для десериализации не совпадает с классом объекта из Json");}
        } else {
            throw new JsonDesirializeException("Не Json объект");
        }

        return clearObject;
    }

    private boolean isStringValue(String string){
        return string.startsWith("\"")&&string.endsWith("\""); //если строка начинается и заканчивается с " то это String
    }
    private void setFieldValue(Object ObjectToSet, String fieldName, String fieldValue) throws JsonDesirializeException, IllegalAccessException {
        Field[] fields=ObjectToSet.getClass().getDeclaredFields(); //вытащили все поля
        for (Field field : fields) {
            if (field.getAnnotation(JSonName.class) != null) { // проверим что поле аннотировано как JsonName
                field.setAccessible(true);
                if (field.getAnnotation(JSonName.class).name().equals(fieldName)) { //имя поле совпадает с именем поля в json
                    if(field.getType()==String.class){
                        if (isStringValue(fieldValue)){

                            field.set(ObjectToSet, fieldValue.replaceAll("\"", ""));
                        } else {
                            throw new JsonDesirializeException("Некорректное значение поле записи");
                        }
                    }
                    if(field.getType()==int.class){
                        if (!isStringValue(fieldValue)){
                            field.set(ObjectToSet, Integer.parseInt(fieldValue));
                        } else {
                            throw new JsonDesirializeException("Некорректное значение поле записи");
                        }

                    }
                    if(field.getType()==double.class){
                        if (!isStringValue(fieldValue)){
                            field.set(ObjectToSet, Double.parseDouble(fieldValue));
                        } else {
                            throw new JsonDesirializeException("Некорректное значение поле записи");
                        }
                    }
                }
            }
        }
    }
}
