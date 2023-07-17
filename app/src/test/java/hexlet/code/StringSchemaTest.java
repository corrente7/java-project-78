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
        assertThat(schema1.isValid("")).isTrue();
        assertThat(schema1.isValid("rt")).isTrue();
        assertThat(schema1.isValid(5)).isFalse();
        schema1.required();
        assertThat(schema1.isValid(null)).isFalse();
        assertThat(schema1.isValid("")).isFalse();
    }

    @Test
    void testContains() {
        assertThat(schema1.contains("at").isValid("what does the fox say")).isTrue();
        assertThat(schema1.contains("rt").isValid("what does the fox say")).isFalse();
    }

    @Test
    void testMinLength() {
        assertThat(schema1.minLength(4).isValid("check")).isTrue();
        assertThat(schema1.minLength(6).isValid("check")).isFalse();
    }
}
