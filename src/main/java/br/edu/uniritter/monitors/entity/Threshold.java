package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.contracts.ThresholdRule;
import br.edu.uniritter.monitors.converters.RuleConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    @Enumerated(EnumType.STRING)
    private Rule rule;

    @NotNull
    private Long threshold;

    public Boolean exceed(Long value) {
        return rule.compare(value, threshold);
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
