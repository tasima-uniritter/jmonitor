package br.edu.uniritter.monitors.constant;

import br.edu.uniritter.monitors.contracts.ThresholdRule;
import br.edu.uniritter.monitors.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Rule {
    // TIMEOUT(new RuleTimeout()),
    EQUAL(new RuleEqual()),
    GREATER_THAN(new RuleGreaterThan()),
    GREATER_THAN_OR_EQUAL_TO(new RuleGreaterThanEqual()),
    LESS_THAN(new RuleLessThan()),
    LESS_THAN_OR_EQUAL_TO(new RuleLessThanEqual()),
    NOT_EQUAL(new RuleNotEqual());

    private ThresholdRule rule;
}
