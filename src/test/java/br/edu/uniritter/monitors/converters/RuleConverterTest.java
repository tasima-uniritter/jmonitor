package br.edu.uniritter.monitors.converters;

import br.edu.uniritter.monitors.contracts.ThresholdRule;
import br.edu.uniritter.monitors.rules.Equal;
import br.edu.uniritter.monitors.rules.GreaterThan;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RuleConverterTest {

    @Test
    public void shouldReturnThresholdDatabaseColumn() {

        // given the threshold rule
        ThresholdRule thresholdRule = new Equal();
        RuleConverter ruleConverter = new RuleConverter();
        // when I convert to database column
        String result = ruleConverter.convertToDatabaseColumn(thresholdRule);

        // then I expect the respective column
        assertEquals(thresholdRule.toString(), result);
    }

    @Test
    public void shouldReturnEqualThresholdRuleEntity() {

        // given the database column
        RuleConverter ruleConverter = new RuleConverter();

        // when I convert to Equal entity
        Equal equal = new Equal();
        ThresholdRule result = ruleConverter.convertToEntityAttribute(equal.toString());

        // then I expect the Equal Entity
        assertEquals(Equal.class, result.getClass());

    }

    @Test
    public void shouldReturnGreaterThenThresholdRuleEntity() {

        // given the database column
        RuleConverter ruleConverter = new RuleConverter();

        // when I convert to Greater Than entity
        GreaterThan greaterThan = new GreaterThan();
        ThresholdRule result = ruleConverter.convertToEntityAttribute(greaterThan.toString());

        // then I expect the  Greater Than entity
        assertEquals(GreaterThan.class, result.getClass());

    }

    @Test
    public void shouldReturnNullThresholdRuleEntity() {

        // given the database column
        RuleConverter ruleConverter = new RuleConverter();

        // when I convert to null entity
        ThresholdRule result = ruleConverter.convertToEntityAttribute("");

        // then I expect null entity
        assertNull(result);

    }


}
