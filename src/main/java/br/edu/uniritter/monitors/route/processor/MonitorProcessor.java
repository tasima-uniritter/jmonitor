package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.IncomeMessage;
import br.edu.uniritter.monitors.entity.OutputMessage;
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
        IncomeMessage incomeMessage = exchange.getIn().getBody(IncomeMessage.class);

        Threshold threshold = thresholdService.findOneByOriginAndMetric(incomeMessage.getOrigin(), incomeMessage.getMetric());
        log.debug("{}", threshold);

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

            OutputMessage outputMessage = new OutputMessage(
                incomeMessage.getOrigin(),
                incomeMessage.getMetric(),
                incomeMessage.getValue(),
                incomeMessage.getTimestamp(),
                threshold.getRule(),
                threshold.getThreshold()
            );
            exchange.getOut().setBody(outputMessage);
            log.debug("shouldAlert true {} > {} {}",
                incomeMessage.getValue(),
                threshold.getThreshold(),
                outputMessage);
        }

        log.debug("<<<<< setShouldAlert");
    }

}
