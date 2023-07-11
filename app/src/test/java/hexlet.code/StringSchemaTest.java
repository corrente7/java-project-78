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
    void testRequired() {
        assertThat(schema1.isValid(null)).isTrue();
        schema1.required();
        boolean expected = schema1.isValid(null);
        assertThat(expected).isFalse();
    }

    @Test
    void testWithNumber() {
        boolean expected = schema1.isValid(5);
        assertThat(expected).isFalse();
    }

    @Test
    void testContains() {
        boolean expected = schema1.contains("at").isValid("what does the fox say");
        assertThat(expected).isTrue();
    }

    @Test
    void testMinLength() {
        boolean expected = schema1.minLength(4).isValid("check");
        assertThat(expected).isTrue();
    }
}
