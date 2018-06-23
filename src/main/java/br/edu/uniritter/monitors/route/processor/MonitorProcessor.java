package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Metric;
import br.edu.uniritter.monitors.entity.Alert;
import br.edu.uniritter.monitors.entity.Threshold;
import br.edu.uniritter.monitors.service.ThresholdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MonitorProcessor {

    @Autowired
    private ThresholdService thresholdService;

    public void getThreshold(Exchange exchange) {
        log.debug(">>>>> getThreshold");
        Metric metric = exchange.getIn().getBody(Metric.class);

        Threshold threshold = thresholdService.findOneByOriginAndMetric(metric.getOrigin(), metric.getMetric());
        log.debug("{}", threshold);

        exchange.getOut().setHeader("threshold", threshold);
        exchange.getOut().setBody(metric);
        log.debug("<<<<< getThreshold");
    }

    public void setShouldAlert(Exchange exchange) {
        log.debug(">>>>> setShouldAlert");

        Metric metric = exchange.getIn().getBody(Metric.class);
        Threshold threshold;
        threshold = exchange.getIn().getHeader("threshold", Threshold.class);
        if (threshold != null && threshold.exceed(metric.getValue())) {
            exchange.getOut().setHeader("shouldAlert", true);

            Alert alert = new Alert(
                metric.getOrigin(),
                metric.getMetric(),
                metric.getValue(),
                metric.getTimestamp(),
                threshold.getRule(),
                threshold.getThreshold()
            );
            exchange.getOut().setBody(alert);
            log.debug("shouldAlert true {} > {} {}",
                metric.getValue(),
                threshold.getThreshold(),
                alert);
        }

        log.debug("<<<<< setShouldAlert");
    }

}
