package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class IncomeMessage {
    private String origin;

    @Enumerated(EnumType.STRING)
    private Metric metric;

    @NotNull
    private Long value;

    private Long timestamp;

    @Override
    public String toString() {

        return String.format("{" +
                "\"origin\": \"%s\"," +
                "\"metric\": \"%s\"," +
                "\"timestamp\": %s," +
                "\"value\": %s" +
                "}", origin, metric, timestamp, value
        );
    }
}
