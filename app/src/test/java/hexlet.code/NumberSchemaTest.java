package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {
    private NumberSchema schema2;

    @BeforeEach
    void initEach() {
        Validator v = new Validator();
        schema2 = v.number();
    }

    @Test
    void TestWithString() {
        boolean expected = schema2.isValid("sth");
        assertThat(expected).isFalse();
    }

    @Test
    void testRequired() {
        assertThat(schema2.isValid(null)).isTrue(); // true
        schema2.required();
        boolean expected = schema2.isValid(null);
        assertThat(expected).isFalse();
    }

    @Test
    void testRange() {
        boolean expected = schema2.range(5, 10).isValid(5);
        assertThat(expected).isTrue();
    }

    @Test
    void testWithNull() {
        assertThat(schema2.isValid(null)).isTrue(); // true
        assertThat(schema2.positive().isValid(7)).isTrue(); // true
        schema2.required();
        boolean expected = schema2.isValid(null); // false
        assertThat(expected).isFalse();
    }

    @Test
    void testPositiveWithNull() {
        assertThat(schema2.isValid(null)).isTrue(); // true
        boolean expected = schema2.positive().isValid(null); // true
        assertThat(expected).isTrue();
    }
}
