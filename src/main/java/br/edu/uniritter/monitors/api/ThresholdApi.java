package br.edu.uniritter.monitors.api;

import br.edu.uniritter.monitors.entity.Threshold;
import br.edu.uniritter.monitors.service.ThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public Threshold store(@Valid @RequestBody Threshold threshold) {
        return threshold;
    }
}
