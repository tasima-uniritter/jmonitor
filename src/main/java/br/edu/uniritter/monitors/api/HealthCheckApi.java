package br.edu.uniritter.monitors.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/health{(check)?}")
public class HealthCheckApi {

    @GetMapping
    String index() {
        return "Green";
    }

}
