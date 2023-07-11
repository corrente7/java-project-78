package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addToList(num -> num == null | num instanceof Integer);
    }

    public NumberSchema required() {
        addToList(num -> num != null);
        return this;
    }

    public NumberSchema positive() {
        addToList(num -> num == null || (Integer) num > 0);
        return this;
    }

    public NumberSchema range(int limit1, int limit2) {
        addToList(num -> num == null || (Integer) (num) >= limit1 && (Integer) (num) <= limit2);
        return this;
    }
}
