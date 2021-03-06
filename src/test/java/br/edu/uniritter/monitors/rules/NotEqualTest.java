package br.edu.uniritter.monitors.rules;

import br.edu.uniritter.monitors.constant.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotEqualTest {

    @Test
    public void shouldReturnTrueWhenValueIsNotEqualThreshold() {

        // given the threshold is 10
        Rule rule = Rule.NOT_EQUAL;
        Long threshold = 10L;

        // when I run compare from value 11
        Boolean result = rule.compare(11L, threshold);

        // then I expect true
        assertEquals(true, result);
    }

    @Test
    public void shouldReturnFalseWhenValueIsEqualThreshold() {

        // given the threshold is 1
        Rule rule = Rule.NOT_EQUAL;
        Long threshold = 1L;

        // when I run compare from value 1
        Boolean result = rule.compare(1L, threshold);

        // then I expect true
        assertEquals(false, result);
    }

}
