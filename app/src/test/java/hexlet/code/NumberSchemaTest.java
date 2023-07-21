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
    void testRequired() {
        assertThat(schema2.isValid("sth")).isFalse(); // false
        assertThat(schema2.isValid(null)).isTrue(); // true
        schema2.required();
        assertThat(schema2.isValid(null)).isFalse();
        assertThat(schema2.isValid(6)).isTrue();
    }

    @Test
    void testRange() {
        assertThat(schema2.range(5, 10).isValid(3)).isFalse();
        assertThat(schema2.range(5, 10).isValid(5)).isTrue();
        assertThat(schema2.range(5, 10).isValid(7)).isTrue();
        assertThat(schema2.range(5, 10).isValid(12)).isFalse();
    }

    @Test
    void testPositive() {
        assertThat(schema2.positive().isValid(null)).isTrue(); // true
        assertThat(schema2.positive().isValid(1)).isTrue(); // true
        assertThat(schema2.positive().isValid(0)).isFalse(); // false
        assertThat(schema2.positive().isValid(-1)).isFalse(); // false
    }
}
