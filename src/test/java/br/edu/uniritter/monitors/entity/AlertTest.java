package br.edu.uniritter.monitors.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import pl.pojo.tester.api.assertion.Method;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.*;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class AlertTest {
    @Test
    public void shouldPassAllPojoTests() {
        // given
        final Class<?> classUnderTest = Alert.class;

        // when
        final Throwable result = catchThrowable(
            () -> assertPojoMethodsFor(classUnderTest)
                .testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE)
                .areWellImplemented()
        );

        // then
        assertNull(result);
    }

    @Test
    public void shouldReturnJsonWhenToStringIsCalld() {
        assertNotNull(new Alert().toString());
    }

    @Test
    public void testJsonProcessingException() throws JsonProcessingException {
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenThrow(
            new JsonProcessingException("Error parsing the object to json string. ") {
            }
        );
        Alert alert = new Alert();
        alert.setObjectMapper(objectMapper);

        assertEquals("", alert.toString());
    }
}
