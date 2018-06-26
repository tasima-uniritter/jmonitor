package br.edu.uniritter.monitors.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import pl.pojo.tester.api.assertion.Method;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.*;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class EventTest {
    @Test
    public void shouldPassAllPojoTests() {
        // given
        final Class<?> classUnderTest = Event.class;

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
    public void shouldReturnJsonWhenToStringIsCalled() {
        assertNotNull(new Event().toString());
    }

    @Test
    public void testJsonProcessingException() throws JsonProcessingException {
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenThrow(
            new JsonProcessingException("Error parsing the object to json string. ") {
            }
        );
        Event event = new Event();
        event.setObjectMapper(objectMapper);

        assertEquals("", event.toString());
    }
}
