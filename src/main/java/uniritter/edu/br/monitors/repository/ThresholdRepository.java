package uniritter.edu.br.monitors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uniritter.edu.br.monitors.entity.Threshold;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Long> {
}
