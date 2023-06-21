package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema implements BaseSchema {

    List<Predicate<String>> list = new ArrayList<>();

    @Override
    public boolean isValid(Object object) {
        boolean checkType = isInstance().test(object);
        return checkType && list.stream().noneMatch(s -> !s.test((String) object));
    }
    @Override
    public Predicate isInstance() {
        return (s -> s instanceof String || s == null);
    }

    public List<Predicate<String>> addToList(Predicate<String> predicate) {
        list.add(predicate);
        return list;
    }

    public StringSchema required() {
        addToList(s -> s != null);
        addToList(s -> !s.equals(""));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addToList(s -> s.length() > minLength);
        return this;
    }

    public StringSchema contains(String content) {
        addToList(s -> s.contains(content));
        return this;
    }
}
