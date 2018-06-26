package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
class EventId implements Serializable {

    private String origin;

    private Metric metric;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventId)) return false;
        EventId that = (EventId) o;
        return Objects.equals(getOrigin(), that.getOrigin())
            && Objects.equals(getMetric(), that.getMetric());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrigin(), getMetric());
    }
}
