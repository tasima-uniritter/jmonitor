package br.edu.uniritter.monitors.converters;

import br.edu.uniritter.monitors.contracts.ThresholdRule;
import br.edu.uniritter.monitors.rules.Equal;
import br.edu.uniritter.monitors.rules.GreaterThan;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RuleConverter implements AttributeConverter<ThresholdRule, String> {
    @Override
    public String convertToDatabaseColumn(ThresholdRule attribute) {
        return attribute.toString();
    }

    @Override
    public ThresholdRule convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "EQUAL": {
                return new Equal();
            }
            case "GREATER_THAN": {
                return new GreaterThan();
            }
            default: {
                return null;
            }
        }
    }
}
