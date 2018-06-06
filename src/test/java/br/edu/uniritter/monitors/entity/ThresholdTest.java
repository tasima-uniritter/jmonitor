package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThresholdTest {

    @Test
    public void shouldReturnTrueWhenMetricLessThanThresholdExceed() {

        // given
        Threshold threshold = new Threshold(1L, "some-origin", Metric.MEMORY_USAGE, Rule.LESS_THAN, 100L);

        IncomeMessage incomeMessage = new IncomeMessage();
        incomeMessage.setMetric(Metric.MEMORY_USAGE);
        incomeMessage.setValue(500L);

        // when
        Boolean exceed = threshold.exceed(incomeMessage);

        assertEquals(true, exceed);
    }

    @Test
    public void shouldReturnFalseWhenMetricLessThanThresholdDoNotExceed() {

        // given
        Threshold threshold = new Threshold(1L, "some-origin", Metric.MEMORY_USAGE, Rule.LESS_THAN, 100L);

        IncomeMessage incomeMessage = new IncomeMessage();
        incomeMessage.setMetric(Metric.MEMORY_USAGE);
        incomeMessage.setValue(99L);

        // when
        Boolean exceed = threshold.exceed(incomeMessage);

        assertEquals(false, exceed);
    }
}
