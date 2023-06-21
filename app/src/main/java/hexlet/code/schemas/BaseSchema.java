package hexlet.code.schemas;

import java.util.function.Predicate;

public interface BaseSchema {

    boolean isValid(Object object);

    Predicate isInstance();

}
