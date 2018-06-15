package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.entity.Threshold;
import br.edu.uniritter.monitors.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThresholdService {

    @Autowired
    private ThresholdRepository thresholdRepository;

    public List<Threshold> all() {
        return thresholdRepository.findAll();
    }

    public Threshold findOneByOriginAndMetric(String origin, Metric metric) {
        return thresholdRepository.findOneByOriginAndMetric(origin, metric);
    }

}
