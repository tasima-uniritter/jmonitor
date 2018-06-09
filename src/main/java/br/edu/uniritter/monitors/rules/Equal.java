package br.edu.uniritter.monitors.rules;

import br.edu.uniritter.monitors.contracts.ThresholdRule;

import java.util.Objects;

public class Equal extends ThresholdRule {

    @Override
    public Boolean compare(Long value, Long threshold) {
        return Objects.equals(value, threshold);
    }
}
