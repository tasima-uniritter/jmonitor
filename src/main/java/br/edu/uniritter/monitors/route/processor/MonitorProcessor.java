package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Alert;
import br.edu.uniritter.monitors.entity.Metric;
import br.edu.uniritter.monitors.entity.Monitor;
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

    public void getMonitor(Exchange exchange) {
        log.debug(">>>>> getMonitor");
        Metric metric = exchange.getIn().getBody(Metric.class);

        Monitor monitor = monitorService.findOneByOriginAndMetric(metric.getOrigin(), metric.getName());
        log.debug("{}", metric);

        exchange.getOut().setHeader("monitor", monitor);
        exchange.getOut().setBody(metric);
        log.debug("<<<<< getMonitor");
    }

    public void setShouldAlert(Exchange exchange) {
        log.debug(">>>>> setShouldAlert");

        Metric metric = exchange.getIn().getBody(Metric.class);
        Monitor monitor;
        monitor = exchange.getIn().getHeader("monitor", Monitor.class);
        if (monitor != null && monitor.compare(metric.getValue())) {
            exchange.getOut().setHeader("shouldAlert", true);

            Alert alert = new Alert(
                metric.getOrigin(),
                metric.getName(),
                metric.getValue(),
                metric.getTimestamp(),
                monitor.getRule(),
                monitor.getThreshold()
            );
            exchange.getOut().setBody(alert);
            log.debug("shouldAlert true {} > {} {}",
                metric.getValue(),
                monitor.getThreshold(),
                alert);
        }

        log.debug("<<<<< setShouldAlert");
    }

}
