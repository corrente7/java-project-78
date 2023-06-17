package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {
    Validator v;
    StringSchema schema;
    @BeforeEach
    void initEach() {
        v = new Validator();
        schema = v.string();
    }
    @Test
    void stringTest1() {
        schema.isValid("");
        schema.required();
        //schema.minLength(5);
        boolean expected = schema.isValid("");
        assertThat(expected).isFalse();
    }

    @Test
    void stringTest2() {
        boolean expected = schema.isValid(5);
        assertThat(expected).isFalse();
    }

    @Test
    void stringTest3() {
        boolean expected = schema.contains("what").isValid("what does the fox say");
        assertThat(expected).isTrue();
    }
}
