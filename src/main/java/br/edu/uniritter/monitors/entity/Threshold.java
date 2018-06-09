package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.contracts.ThresholdRule;
import br.edu.uniritter.monitors.converters.RuleConverter;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.edu.uniritter.monitors.constant.Metric;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.impl.converter.ToStringTypeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "thresholds")
public class Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String origin;

    @Enumerated(EnumType.STRING)
    private Metric metric;

    @Convert(converter = RuleConverter.class)
    private ThresholdRule rule;

    @NotNull
    private Long threshold;

    public Boolean exceed(Long value) {
        return rule.compare(value, threshold);
    }
}
