package br.edu.uniritter.monitors.entity;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.assertNull;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class EventIdTest {
    @Test
    public void shouldPassAllPojoTests() {
        // given
        final Class<?> classUnderTest = EventId.class;

        // when
        final Throwable result = catchThrowable(
            () -> assertPojoMethodsFor(classUnderTest)
                .testing(Method.CONSTRUCTOR, Method.GETTER, Method.EQUALS, Method.HASH_CODE)
                .areWellImplemented()
        );

        // then
        assertNull(result);
    }
}
