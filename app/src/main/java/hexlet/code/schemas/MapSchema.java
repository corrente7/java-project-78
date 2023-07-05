package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addToList(map -> map == null || map instanceof Map);
    }

    @Override
    public boolean isValid(Object object) {
        return super.isValid(object);
    }

    public MapSchema required() {
        addToList(map -> map != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addToList(map -> {
            boolean result = false;
            Map map1 = (Map) map;
            if (map1.size() == size) {
            result = true;
            }
            return result;
        });
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addToList(map -> {
            boolean result = false;
            Map map1 = (Map) map;
            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                if (map1.containsKey(entry.getKey())) {
                    result = entry.getValue().isValid(map1.get(entry.getKey()));
                    if (!result) {
                        break;
                    }
                }
            }
            return result;
        });
        return this;
    }
}
