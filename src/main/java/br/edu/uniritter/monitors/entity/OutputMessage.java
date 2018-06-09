package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.contracts.ThresholdRule;
import br.edu.uniritter.monitors.converters.RuleConverter;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OutputMessage {
    private String origin;

    @Enumerated(EnumType.STRING)
    private Metric metric;

    @NotNull
    private Long value;

    private Long timestamp;

    @Convert(converter = RuleConverter.class)
    private ThresholdRule rule;

    private Long threshold;

    @Override
    public String toString() {

        Gson g = new Gson();
        return g.toJson(this);

//        return String.format("{" +
//            "\"origin\": \"%s\"," +
//            "\"metric\": \"%s\"," +
//            "\"timestamp\": %s," +
//            "\"value\": %s," +
//            "\"rule\": \"%s\"," +
//            "\"threshold\": %s" +
//            "}", origin, metric, timestamp, value, rule, threshold
//        );
    }
}
