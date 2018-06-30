package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
class EventId implements Serializable {
    private String origin;
    private Metric metric;
}
