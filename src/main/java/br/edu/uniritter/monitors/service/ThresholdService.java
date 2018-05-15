package br.edu.uniritter.monitors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.uniritter.monitors.repository.ThresholdRepository;

@Service
public class ThresholdService {

    @Autowired
    private ThresholdRepository thresholdRepository;

}
