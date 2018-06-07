package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.contracts.ThresholdRule;

public class RuleGreaterThanEqual implements ThresholdRule {

    @Override
    public Boolean compare(Long value, Long threshold) {
        return value >= threshold;
    }

    @Override
    public String toString() {
        return "GREATER_THAN_OR_EQUAL_TO";
    }
}
