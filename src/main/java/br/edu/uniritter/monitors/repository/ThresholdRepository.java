package br.edu.uniritter.monitors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.uniritter.monitors.entity.Threshold;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Long> {
}
