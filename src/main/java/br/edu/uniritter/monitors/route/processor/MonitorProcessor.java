package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Alert;
import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.entity.Monitor;
import br.edu.uniritter.monitors.repository.EventRepository;
import br.edu.uniritter.monitors.service.EventService;
import br.edu.uniritter.monitors.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MonitorProcessor {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private EventService eventService;

    public void saveEvent(Event event) {
        log.debug("Event saved ---->>> {}", eventService.save(event));
    }

    public void getMonitor(Exchange exchange) {
        log.debug(">>>>> getMonitor");
        Event event = exchange.getIn().getBody(Event.class);

        Monitor monitor = monitorService.findOneByOriginAndMetric(event.getOrigin(), event.getMetric());
        log.debug("monitor {}found {}", monitor == null ? "not " : "", monitor);

        exchange.getOut().setHeader("monitor", monitor);
        exchange.getOut().setBody(event);
        log.debug("<<<<< getMonitor");
    }

    public void setShouldAlert(Exchange exchange) {
        log.debug(">>>>> setShouldAlert");

        Event metric = exchange.getIn().getBody(Event.class);
        Monitor monitor;
        monitor = exchange.getIn().getHeader("monitor", Monitor.class);
        if (monitor != null && monitor.compare(metric.getValue())) {
            exchange.getOut().setHeader("shouldAlert", true);

            Alert alert = new Alert();
            alert.setOrigin(metric.getOrigin());
            alert.setMetric(metric.getMetric());
            alert.setValue(metric.getValue());
            alert.setTimestamp(metric.getTimestamp());
            alert.setRule(monitor.getRule());
            alert.setThreshold(monitor.getThreshold());
            exchange.getOut().setBody(alert);
            log.debug("shouldAlert true {} > {} {}",
                metric.getValue(),
                monitor.getThreshold(),
                alert);
        }

        log.debug("<<<<< setShouldAlert");
    }

}
