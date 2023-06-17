package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema implements BaseSchema{
    public static void main(String[] args) {
        Validator v = new Validator();
        StringSchema schema = v.string();
        //schema.minLength(8);
        //schema.contains("qw");
        //schema.isValid("");
        schema.contains("whatthe").isValid("what does the fox say");
        //System.out.println(schema.isValid("hghgh"));
        System.out.println(schema.isValid("what does the fox say"));
    }
    List<Predicate<String>> list = new ArrayList<>();

    @Override
    public boolean isValid(Object object) {
        boolean checkType = isInstance().test(object);
        //list.forEach(predicate -> System.out.println(predicate.test((String) object)));
        return checkType && list.stream().noneMatch(s -> s.test((String) object) == false);
    }
   @Override
    public Predicate isInstance() {
        return (s -> s instanceof String);
    }

    public List<Predicate<String>> addToList(Predicate<String> predicate) {
        list.add(predicate);
        return list;
    }

    public StringSchema required() {
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
