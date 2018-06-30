package br.edu.uniritter.monitors.dto;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import lombok.Data;

@Data
public class MonitorDTO {
    private Long id;

    private String origin;

    private Metric metric;

    private Rule rule;

    private Long threshold;
}
