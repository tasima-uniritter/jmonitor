package br.edu.uniritter.monitors.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/thresholds")
public class ThresholdApi {

    @GetMapping
    String index() {
        return "list of thresholds";
    }

}
