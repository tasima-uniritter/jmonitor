package uniritter.edu.br.monitors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniritter.edu.br.monitors.repository.ThresholdRepository;

@Service
public class ThresholdService {

    @Autowired
    private ThresholdRepository thresholdRepository;

}
