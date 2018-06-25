package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.MetricName;
import br.edu.uniritter.monitors.constant.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonitorTest {

    @Test
    public void shouldReturnTrueWhenMetricGreaterThanThresholdExceed() {

        // given
        Monitor monitor = new Monitor(1L, "some-origin", MetricName.MEMORY_USAGE, Rule.GREATER_THAN, 100L);

        // when
        Boolean exceed = monitor.compare(500L);

        assertEquals(true, exceed);
    }

    @Test
    public void shouldReturnFalseWhenMetricGreaterThanThresholdDoNotExceed() {

        // given
        Monitor monitor = new Monitor(1L, "some-origin", MetricName.MEMORY_USAGE, Rule.GREATER_THAN, 100L);

        // when
        Boolean exceed = monitor.compare(99L);

        assertEquals(false, exceed);
    }
}
