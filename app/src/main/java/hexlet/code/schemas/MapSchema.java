package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addToList(map -> map == null || map instanceof Map);
    }

    public MapSchema sizeof(int size) {
        addToList(map -> map == null || ((Map) map).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addToList(map -> schemas.entrySet()
                .stream()
                .allMatch(e -> e.getValue().isValid(((Map) map).get(e.getKey()))));
        return this;
    }
}
