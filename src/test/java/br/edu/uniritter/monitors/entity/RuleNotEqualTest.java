package br.edu.uniritter.monitors.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RuleNotEqualTest {

    @Test
    public void shouldReturnTrueWhenValueIsNotEqualThreshold() {

        // given the threshold is 10
        RuleNotEqual rule = new RuleNotEqual();
        Long threshold = 10L;

        // when I run compare from value 11
        Boolean result = rule.compare(11L, threshold);

        // then I expect true
        assertEquals(true, result);
    }

    @Test
    public void shouldReturnFalseWhenValueIsEqualThreshold() {

        // given the threshold is 1
        RuleNotEqual rule = new RuleNotEqual();
        Long threshold = 1L;

        // when I run compare from value 1
        Boolean result = rule.compare(1L, threshold);

        // then I expect true
        assertEquals(false, result);
    }

}
