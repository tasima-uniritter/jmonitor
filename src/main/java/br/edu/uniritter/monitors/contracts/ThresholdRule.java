package br.edu.uniritter.monitors.contracts;

public interface ThresholdRule {
    Boolean compare(Long value, Long threshold);
}
