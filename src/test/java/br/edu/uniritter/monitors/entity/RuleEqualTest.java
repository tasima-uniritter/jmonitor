package br.edu.uniritter.monitors.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RuleEqualTest {

    @Test
    public void shouldReturnTrueWhenValueIsEqualThreshold() {

        // given the threshold is 10
        RuleEqual rule = new RuleEqual();
        Long threshold = 10L;

        // when I run compare from value 101
        Boolean result = rule.compare(10L, threshold);

        // then I expect true
        assertEquals(true, result);
    }

    @Test
    public void shouldReturnFalseWhenValueIsNotEqualThreshold() {

        // given the threshold is 100
        RuleEqual rule = new RuleEqual();
        Long threshold = 100L;

        // when I run compare from value 100
        Boolean result = rule.compare(1L, threshold);

        // then I expect true
        assertEquals(false, result);
    }

}
