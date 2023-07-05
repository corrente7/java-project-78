package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    List<Predicate> list = new ArrayList<>();

    /**
     * Checks validity of the scheme
     */
    public boolean isValid(Object object) {
        return list.stream().allMatch(s -> s.test(object));
    }

    /**
     * Adds a predicate to the predicates list
     */
    public List<Predicate> addToList(Predicate predicate) {
        list.add(predicate);
        return list;
    }
}
