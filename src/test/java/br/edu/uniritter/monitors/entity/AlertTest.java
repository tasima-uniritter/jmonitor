package br.edu.uniritter.monitors.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AlertTest {
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
