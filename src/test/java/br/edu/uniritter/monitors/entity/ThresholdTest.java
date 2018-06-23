package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.rules.GreaterThan;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThresholdTest {

    @Test
    public void shouldReturnTrueWhenMetricGreaterThanThresholdExceed() {

        // given
        Threshold threshold = new Threshold(1L, "some-origin", Metric.MEMORY_USAGE, Rule.GREATER_THAN, 100L);

        // when
        Boolean exceed = threshold.exceed(500L);

        assertEquals(true, exceed);
    }

    @Test
    public void shouldReturnFalseWhenMetricGreaterThanThresholdDoNotExceed() {

        // given
        Threshold threshold = new Threshold(1L, "some-origin", Metric.MEMORY_USAGE, Rule.GREATER_THAN, 100L);

        // when
        Boolean exceed = threshold.exceed(99L);

        assertEquals(false, exceed);
    }
}
