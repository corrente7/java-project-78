package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        this.addToList(s -> s == null || s instanceof String);
    }

    @Override
    public boolean isValid(Object object) {
        return super.isValid(object);
    }


    public StringSchema required() {
        addToList(s -> s != null);
        addToList(s -> !s.equals(""));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addToList(s -> {
            String s1 = (String) s;
            return s1.length() > minLength;
        });
        return this;
    }

    public StringSchema contains(String content) {
        addToList(s -> {
            String s1 = (String) s;
            return s1.contains(content);
        });
        return this;
    }
}
