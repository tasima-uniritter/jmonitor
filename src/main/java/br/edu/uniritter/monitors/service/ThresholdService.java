package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.entity.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.uniritter.monitors.repository.ThresholdRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ThresholdService {

    @Autowired
    private ThresholdRepository thresholdRepository;

    public List<Threshold> all() {
        return thresholdRepository.findAll();
    }

}
