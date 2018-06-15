package br.edu.uniritter.monitors.repository;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.entity.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Long> {
    Threshold findOneByOriginAndMetric(String origin, Metric metric);
}
