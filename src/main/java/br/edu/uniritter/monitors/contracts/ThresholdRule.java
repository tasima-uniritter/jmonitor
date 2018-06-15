package br.edu.uniritter.monitors.contracts;

import br.edu.uniritter.monitors.converters.ThresholdRuleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

@JsonSerialize(using = ThresholdRuleSerializer.class)
public abstract class ThresholdRule {
    public abstract Boolean compare(Long value, Long threshold);

    public String toString() {
        return UPPER_CAMEL.to(UPPER_UNDERSCORE, this.getClass().getSimpleName());
    }
}
