package br.edu.uniritter.monitors.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventTest {
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
