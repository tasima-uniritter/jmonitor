package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.entity.IncomeMessage;
import br.edu.uniritter.monitors.entity.Threshold;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MonitorProcessor {

    public void getThreshold(Exchange exchange) {
        log.debug(">>>>> getThreshold");
        IncomeMessage incomeMessage = exchange.getIn().getBody(IncomeMessage.class);

        // TODO get from database
        Threshold threshold = new Threshold();
        threshold.setMetric(Metric.MEMORY_USAGE);
        threshold.setOrigin(incomeMessage.getOrigin());
        threshold.setRule(Rule.GREATER_THAN);
        threshold.setThreshold(300L);

        exchange.getOut().setHeader("threshold", threshold);
        exchange.getOut().setBody(incomeMessage);
        log.debug("<<<<< getThreshold");
    }

    public void setShouldAlert(Exchange exchange) {
        log.debug(">>>>> setShouldAlert");

        IncomeMessage incomeMessage = exchange.getIn().getBody(IncomeMessage.class);
        Threshold threshold;
        threshold = exchange.getIn().getHeader("threshold", Threshold.class);
        if (threshold != null && threshold.exceed(incomeMessage.getValue())) {
//            log.debug("shouldAlert true {} > {} {}",
//                incomeMessage.getValue(),
//                threshold.getThreshold(),
//                incomeMessage.getValue() > threshold.getThreshold());
            exchange.getOut().setHeader("shouldAlert", true);
        }
        exchange.getOut().setBody(incomeMessage);

        log.debug("<<<<< setShouldAlert");
    }

}
