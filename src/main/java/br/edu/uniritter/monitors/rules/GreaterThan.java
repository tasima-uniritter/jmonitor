package br.edu.uniritter.monitors.rules;

import br.edu.uniritter.monitors.contracts.ThresholdRule;

import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

public class GreaterThan extends ThresholdRule {

    @Override
    public Boolean compare(Long value, Long threshold) {
        return value > threshold;
    }

    public String toString() {
        return UPPER_CAMEL.to(UPPER_UNDERSCORE, this.getClass().getSimpleName());
    }
}
