package br.edu.uniritter.monitors.api;

import br.edu.uniritter.monitors.entity.Threshold;
import br.edu.uniritter.monitors.service.ThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/thresholds")
public class ThresholdApi {

    @Autowired
    ThresholdService thresholdService;

    @GetMapping
    public List<Threshold> index() {
        return thresholdService.all();
    }
}
