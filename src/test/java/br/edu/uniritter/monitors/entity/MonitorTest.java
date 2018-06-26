package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import pl.pojo.tester.api.assertion.Method;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class MonitorTest {

    @Test
    public void shouldPassAllPojoTests() {
        // given
        final Class<?> classUnderTest = Monitor.class;

        // when
        final Throwable result = catchThrowable(
            () -> assertPojoMethodsFor(classUnderTest)
                .testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER, Method.EQUALS)
                .areWellImplemented()
        );

        // then
        assertNull(result);
    }

    @Test
    public void compareShouldCallRuleCompare() {

        // given
        Rule rule = mock(Rule.class);
        Monitor monitor = new Monitor();
        monitor.setThreshold(anyLong());
        when(rule.compare(anyLong(), monitor.getThreshold())).thenReturn(true);
        monitor.setRule(rule);

        // when
        Boolean exceed = monitor.compare(100L);

        // then
        verify(rule, times(1)).compare(eq(100L), eq(monitor.getThreshold()));
        assertTrue(exceed);
    }

    @Test
    public void shouldReturnJsonWhenToStringIsCalled() {
        assertNotNull(new Monitor().toString());
    }

    @Test
    public void testJsonProcessingException() throws JsonProcessingException {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        Mockito.when(objectMapper.writeValueAsString(any())).thenThrow(
            new JsonProcessingException("Error parsing the object to json string. ") {
            }
        );
        Monitor monitor = new Monitor();
        monitor.setObjectMapper(objectMapper);

        assertEquals("", monitor.toString());
    }
}
