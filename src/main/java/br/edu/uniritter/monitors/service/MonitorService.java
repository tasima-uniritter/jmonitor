package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.entity.Monitor;
import br.edu.uniritter.monitors.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private MonitorRepository monitorRepository;

    public List<Monitor> all() {
        return monitorRepository.findAll();
    }

    public Monitor findOneByOriginAndMetric(String origin, Metric metric) {
        return monitorRepository.findOneByOriginAndMetric(origin, metric);
    }

    public Monitor save(Monitor monitor) {
        return monitorRepository.save(monitor);
    }
}
