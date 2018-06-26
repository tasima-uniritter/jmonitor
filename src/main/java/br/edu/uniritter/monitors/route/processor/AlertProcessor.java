package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.entity.Alert;
import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.entity.Monitor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlertProcessor {

    public Alert buildTimeoutAlert(@Header("monitor") Monitor monitor, @Body Event event) {
        log.debug(">>>>> AlertProcessor {} {}", Rule.TIMEOUT, event);
        Alert alert = new Alert();
        alert.setOrigin(event.getOrigin());
        alert.setMetric(event.getMetric());
        alert.setValue(event.getValue());
        alert.setTimestamp(event.getTimestamp());
        alert.setRule(Rule.TIMEOUT);
        alert.setThreshold(monitor.getThreshold());
        log.debug("<<<<< AlertProcessor");
        return alert;
    }

    public Alert buildAlert(@Header("monitor") Monitor monitor, @Body Event event) {
        log.debug(">>>>> AlertProcessor {} {}", monitor, event);
        Alert alert = new Alert();
        alert.setOrigin(event.getOrigin());
        alert.setMetric(event.getMetric());
        alert.setValue(event.getValue());
        alert.setTimestamp(event.getTimestamp());
        alert.setRule(Rule.TIMEOUT);
        alert.setThreshold(monitor.getThreshold());
        log.debug("<<<<< AlertProcessor");
        return alert;
    }
}
