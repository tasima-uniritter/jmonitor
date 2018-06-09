package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.rules.GreaterThan;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreaterThanTest {

    @Test
    public void shouldReturnTrueWhenValueIsGreaterThanThreshold() {

        // given the threshold is 100
        GreaterThan rule = new GreaterThan();
        Long threshold = 100L;

        // when I run compare from value 101
        Boolean result = rule.compare(101L, threshold);

        // then I expect true
        assertEquals(true, result);
    }

    @Test
    public void shouldReturnFalseWhenValueIsLessThanThreshold() {

        // given the threshold is 100
        GreaterThan rule = new GreaterThan();
        Long threshold = 100L;

        // when I run compare from value 99
        Boolean result = rule.compare(99L, threshold);

        // then I expect true
        assertEquals(false, result);
    }

    @Test
    public void shouldReturnFalseWhenValueIsEqualToThreshold() {

        // given the threshold is 100
        GreaterThan rule = new GreaterThan();
        Long threshold = 100L;

        // when I run compare from value 100
        Boolean result = rule.compare(100L, threshold);

        // then I expect true
        assertEquals(false, result);
    }

}
