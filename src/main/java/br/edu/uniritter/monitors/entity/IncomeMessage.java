package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class IncomeMessage {
    private String origin;

    @Enumerated(EnumType.STRING)
    private Metric metric;

    private Long value;

    private Double timestamp;

    @Override
    public String toString() {

        return String.format("{" +
                "origin: %s," +
                "metric: %s," +
                "timestamp: %s," +
                "value: %s" +
                "}", origin, metric, timestamp, value
        );
    }
}
