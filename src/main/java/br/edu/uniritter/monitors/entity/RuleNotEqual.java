package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.contracts.ThresholdRule;

import java.util.Objects;

public class RuleNotEqual implements ThresholdRule {

    @Override
    public Boolean compare(Long value, Long threshold) {
        return !Objects.equals(value, threshold);
    }

    @Override
    public String toString() {
        return "NOT_EQUAL";
    }

}
