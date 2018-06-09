package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.contracts.ThresholdRule;
import lombok.ToString;

//@ToString
public class RuleGreaterThan implements ThresholdRule {

    @Override
    public Boolean compare(Long value, Long threshold) {
        return value > threshold;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
