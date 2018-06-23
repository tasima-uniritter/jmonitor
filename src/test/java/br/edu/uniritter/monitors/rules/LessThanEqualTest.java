package br.edu.uniritter.monitors.rules;

import br.edu.uniritter.monitors.constant.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LessThanEqualTest {

    @Test
    public void shouldReturnTrueWhenValueIsLessThanThreshold() {

        // given the threshold is 100
        Rule rule = Rule.LESS_THAN_EQUAL;
        Long threshold = 100L;

        // when I run compare from value 99
        Boolean result = rule.compare(99L, threshold);

        // then I expect true
        assertEquals(true, result);
    }

    @Test
    public void shouldReturnFalseWhenValueIsGreaterThanThreshold() {

        // given the threshold is 100
        Rule rule = Rule.LESS_THAN_EQUAL;
        Long threshold = 100L;

        // when I run compare from value 101
        Boolean result = rule.compare(101L, threshold);

        // then I expect true
        assertEquals(false, result);
    }

    @Test
    public void shouldReturnTrueWhenValueIsEqualToThreshold() {

        // given the threshold is 100
        Rule rule = Rule.LESS_THAN_EQUAL;
        Long threshold = 100L;

        // when I run compare from value 100
        Boolean result = rule.compare(100L, threshold);

        // then I expect false
        assertEquals(true, result);
    }

}
