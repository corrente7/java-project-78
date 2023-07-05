package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {
    private StringSchema schema1;

    @BeforeEach
    void initEach() {
        Validator v = new Validator();
        schema1 = v.string();
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
}
