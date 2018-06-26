package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.*;
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
    public void shouldReturnTrueWhenMetricGreaterThanThresholdExceed() {

        // given
        Monitor monitor = new Monitor(1L, "some-origin", Metric.MEMORY_USAGE, Rule.GREATER_THAN, 100L);

        // when
        Boolean exceed = monitor.compare(500L);

        assertEquals(true, exceed);
    }

    @Test
    public void shouldReturnFalseWhenMetricGreaterThanThresholdDoNotExceed() {

        // given
        Monitor monitor = new Monitor(1L, "some-origin", Metric.MEMORY_USAGE, Rule.GREATER_THAN, 100L);

        // when
        Boolean exceed = monitor.compare(99L);

        assertEquals(false, exceed);
    }
}
