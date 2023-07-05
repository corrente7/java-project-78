package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest {

    private MapSchema schema3;
    private Validator v;

    @BeforeEach
    void initEach() {
        v = new Validator();
        schema3 = v.map();
    }

    @Test
    void mapTest1() {
        schema3.isValid(null); // true
        schema3.required();
        boolean expected = schema3.isValid(null); // false
        assertThat(expected).isFalse();
    }

    @Test
    void mapTest2() {
        schema3.isValid(new HashMap()); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        schema3.isValid(data); // true
        schema3.sizeof(2);
        boolean expected = schema3.isValid(data); // false
        assertThat(expected).isFalse();
    }

    @Test
    void mapTest3() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema3.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        boolean expected = schema3.isValid(human1); // true
        assertThat(expected).isTrue();
    }

    @Test
    void mapTest4() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema3.shape(schemas);
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        boolean expected = schema3.isValid(human2); // true
        assertThat(expected).isTrue();
    }

    @Test
    void mapTest5() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema3.shape(schemas);
        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        boolean expected = schema3.isValid(human3); // false
        assertThat(expected).isFalse();
    }

    @Test
    void mapTest6() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema3.shape(schemas);
        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        boolean expected = schema3.isValid(human4); // false
        assertThat(expected).isFalse();
    }
}
