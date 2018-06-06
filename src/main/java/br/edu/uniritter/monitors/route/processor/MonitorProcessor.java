package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.entity.IncomeMessage;
import br.edu.uniritter.monitors.entity.Threshold;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MonitorProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {

        exchange.getOut().setHeader("shouldAlert", false);

        IncomeMessage incomeMessage = exchange.getIn().getBody(IncomeMessage.class);

        log.debug("income message {}", incomeMessage.getValue().toString());
        log.debug("income message {}", incomeMessage.getMetric().toString());
        log.debug(incomeMessage.getValue().toString());
        if (incomeMessage.getValue() > 100) { // @TODO use rule
            exchange.getOut().setHeader("shouldAlert", true);
        }
        exchange.getOut().setBody(incomeMessage);
    }

    public IncomeMessage getThreshold(IncomeMessage incomeMessage) {
        log.debug(">>>>> getting threshold");

        Threshold threshold = new Threshold();
        threshold.setMetric(Metric.MEMORY_USAGE);
        threshold.setOrigin(incomeMessage.getOrigin());
        threshold.setRule(Rule.GREATER_THAN);
        threshold.setThreshold(Long.getLong("100"));

        log.debug("<<<<< getting threshold");
        return incomeMessage;

    }

//    public IncomeMessage getThreshold(IncomeMessage incomeMessage) {
//        log.debug(">>>>> getting threshold");
//
//        log.debug("<<<<< getting threshold");
//        return incomeMessage;
//
//    }
}
