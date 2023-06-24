package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema implements BaseSchema {

    private List<Predicate<Map>> list = new ArrayList<>();
    @Override
    public boolean isValid(Object object) {
        boolean checkType = isInstance().test(object);
        return checkType && list.stream().noneMatch(s -> !s.test((Map) object));
    }

    @Override
    public Predicate isInstance() {
        return (s -> s instanceof Map || s == null);
    }

    public List<Predicate<Map>> addToList(Predicate<Map> predicate) {
        list.add(predicate);
        return list;
    }

    public MapSchema required() {
        addToList(s -> s != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addToList(map -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addToList(map -> {
            boolean result = false;
            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                if (map.containsKey(entry.getKey())) {
                    result = entry.getValue().isValid(map.get(entry.getKey()));
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
