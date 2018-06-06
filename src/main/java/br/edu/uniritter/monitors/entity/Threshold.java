package br.edu.uniritter.monitors.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public Boolean exceed(IncomeMessage incomeMessage) {

        return  (incomeMessage.getValue() > threshold);
//        return true;
    }
}
