package ir.fanfoot.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sorted {

    public enum Sort {
        ASCENDING,
        DESCENDING
    }

    Sort sort() default Sort.ASCENDING;

    int order();
}
