package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        this.addToList(s -> s == null || s instanceof String);
    }

    public StringSchema required() {
        addToList(s -> s != null && !s.equals(""));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addToList(s -> s == null || ((String) s).length() >= minLength);
        return this;
    }

    public StringSchema contains(String content) {
        addToList(s -> s == null || ((String) s).contains(content));
        return this;
    }
}
