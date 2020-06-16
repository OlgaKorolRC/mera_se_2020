package VostrikovaE.Lesson8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // аннотации для полей
@Retention(RetentionPolicy.RUNTIME) //аннотация доступна во время выполнения
public @interface JSonIgnore {
}
