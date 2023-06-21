package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumberSchema implements BaseSchema {

    List<Predicate<Integer>> list = new ArrayList<>();

    @Override
    public boolean isValid(Object object) {
        boolean checkType = isInstance().test(object);
        return checkType && list.stream().noneMatch(s -> !s.test(((Integer) object)));
    }
    @Override
    public Predicate isInstance() {
        return (num -> num == null | num instanceof Integer);
    }

    public List<Predicate<Integer>> addToList(Predicate<Integer> predicate) {
        list.add(predicate);
        return list;
    }
    public NumberSchema required() {
        addToList(num -> num != null);
        return this;
    }

    public NumberSchema positive() {
        addToList(num -> num == null || num > 0);
        return this;
    }

    public NumberSchema range(int limit1, int limit2) {
        addToList(num -> (num >= limit1) && (num <= limit2));
        return this;
    }
}
