package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
@NoArgsConstructor
@IdClass(EventId.class)
@Entity(name = "events")
public class Event {
    @JsonIgnore
    @Transient
    ObjectMapper objectMapper = new ObjectMapper();

    @Id
    @NotNull
    private String origin;

    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    private Metric metric;

    @NotNull
    private Long value;

    @NotNull
    private Long timestamp;

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }
}
