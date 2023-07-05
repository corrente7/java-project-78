package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
       this.addToList(num -> num == null | num instanceof Integer);
    }

    @Override
    public boolean isValid(Object object) {
        return super.isValid(object);
    }

    public NumberSchema required() {
        addToList(num -> num != null);
        return this;
    }

    public NumberSchema positive() {
        addToList(num -> {
            return Objects.equals(num, null) || (Integer) num > 0;
        });
        return this;
    }

    public NumberSchema range(int limit1, int limit2) {
        addToList(num -> {
            return (Integer) (num) >= limit1 && (Integer) (num) <= limit2;
        });
        return this;
    }
}
