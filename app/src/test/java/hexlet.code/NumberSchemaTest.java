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
}
