package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private List<Predicate> predicates = new ArrayList<>();

    /**
     * Checks validity of the scheme.
     * @param object
     * @return scheme is valid or not
     */
    public boolean isValid(Object object) {

        return predicates.stream().allMatch(s -> s.test(object));
    }

    /**
     * Adds a predicate to the predicates list.
     * @param predicate
     * @return list of the predicates
     */
    protected List<Predicate> addToList(Predicate predicate) {
        predicates.add(predicate);
        return predicates;
    }

    /**
     * Doesn't permit to use 'null' as a value.
     * @return schema
     */

    public BaseSchema required() {
        addToList(s -> s != null);
        return this;
    }
}
