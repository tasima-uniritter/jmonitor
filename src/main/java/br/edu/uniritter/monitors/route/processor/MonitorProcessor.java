package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.entity.Monitor;
import br.edu.uniritter.monitors.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MonitorProcessor {

    private static final String MONITOR_HEADER = "monitor";

    @Autowired
    private MonitorService monitorService;

    public void getMonitor(Exchange exchange) {
        log.debug(">>>>> getMonitor");
        Event event = exchange.getIn().getBody(Event.class);

        Monitor monitor = monitorService.findOneByOriginAndMetric(event.getOrigin(), event.getMetric());
        log.debug("monitor {}found {}", monitor == null ? "not " : "", monitor);

        exchange.getOut().setHeader(MONITOR_HEADER, monitor);
        exchange.getOut().setBody(event);
        log.debug("<<<<< getMonitor");
    }

    public void setShouldAlert(Exchange exchange) {
        log.debug(">>>>> setShouldAlert");

        Event event = exchange.getIn().getBody(Event.class);
        Monitor monitor;
        monitor = exchange.getIn().getHeader(MONITOR_HEADER, Monitor.class);
        if (monitor != null && monitor.compare(event.getValue())) {
            exchange.getOut().setHeader(MONITOR_HEADER, monitor);
            exchange.getOut().setHeader("shouldAlert", true);
            exchange.getOut().setBody(event);
            log.debug("shouldAlert true {} > {}",
                event.getValue(),
                monitor.getThreshold());
        }

        log.debug("<<<<< setShouldAlert");
    }

}
