package br.edu.uniritter.monitors.repository;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
    Monitor findOneByOriginAndMetric(String origin, Metric metric);
}
