package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {
    private StringSchema schema1;
    private NumberSchema schema2;
    private MapSchema schema3;
    private Validator v;

    @BeforeEach
    void initEach() {
        v = new Validator();
        schema1 = v.string();
        schema2 = v.number();
        schema3 = v.map();
    }
    @Test
    void stringTest1() {
        schema1.isValid(null);
        schema1.required();
        //schema.minLength(5);
        boolean expected = schema1.isValid(null);
        assertThat(expected).isFalse();
    }

    @Test
    void stringTest2() {
        boolean expected = schema1.isValid(5);
        assertThat(expected).isFalse();
    }

    @Test
    void stringTest3() {
        boolean expected = schema1.contains("at").isValid("what does the fox say");
        assertThat(expected).isTrue();
    }

    @Test
    void numberTest1() {
        boolean expected = schema2.isValid("sth");
        assertThat(expected).isFalse();
    }

    @Test
    void numberTest2() {
        schema2.isValid(null);
        schema2.required();
        boolean expected = schema2.isValid(null);
        assertThat(expected).isFalse();
    }

    @Test
    void numberTest3() {
        boolean expected = schema2.range(5, 10).isValid(5);
        assertThat(expected).isTrue();
    }

    @Test
    void numberTest4() {
        schema2.isValid(null);
        schema2.positive().isValid(7); // true
        schema2.required();
        boolean expected = schema2.isValid(null); // false
        assertThat(expected).isFalse();
    }

    @Test
    void numberTest5() {
        schema2.isValid(null);
        boolean expected = schema2.positive().isValid(null); // true
        assertThat(expected).isTrue();
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

