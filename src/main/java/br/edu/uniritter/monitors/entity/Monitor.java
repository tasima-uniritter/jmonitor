package br.edu.uniritter.monitors.entity;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
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
@Entity(name = "monitors")
public class Monitor {
    @JsonIgnore
    @Transient
    ObjectMapper objectMapper = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String origin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Metric metric;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rule rule;

    @NotNull
    private Long threshold;

    public Boolean compare(Long value) {
        return rule.compare(value, threshold);
    }

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
