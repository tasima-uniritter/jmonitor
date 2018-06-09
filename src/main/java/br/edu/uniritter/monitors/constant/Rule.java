package br.edu.uniritter.monitors.constant;

import br.edu.uniritter.monitors.contracts.ThresholdRule;
import br.edu.uniritter.monitors.entity.*;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;


@AllArgsConstructor
@Getter
public enum Rule {
    // TIMEOUT(new RuleTimeout()),
    EQUAL(new RuleEqual()),
    @SerializedName("greater-than")
    GREATER_THAN(new RuleGreaterThan()),
    GREATER_THAN_OR_EQUAL_TO(new RuleGreaterThanEqual()),
    LESS_THAN(new RuleLessThan()),
    LESS_THAN_OR_EQUAL_TO(new RuleLessThanEqual()),
    NOT_EQUAL(new RuleNotEqual());

    private ThresholdRule rule;

    public String toString() {
        return UPPER_CAMEL
            .to(LOWER_HYPHEN, this.rule.toString())
            .replace("rule-", "");
    }
}
