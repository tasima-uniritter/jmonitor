package br.edu.uniritter.monitors.entity;

import lombok.Data;

@Data
public class IncomeMessage {
    private String origin;
    private String metric;
    private Double value;
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
