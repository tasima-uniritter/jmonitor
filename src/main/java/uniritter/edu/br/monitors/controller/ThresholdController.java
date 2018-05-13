package uniritter.edu.br.monitors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/thresholds")
public class ThresholdController {

    @GetMapping
    String index() {
        return "list of thresholds";
    }

}
