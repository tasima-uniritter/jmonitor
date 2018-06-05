package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.IncomeMessage;
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

        log.debug(incomeMessage.getValue().toString());
        if (incomeMessage.getValue() > 100) { // @TODO use rule
            exchange.getOut().setHeader("shouldAlert", true);
        }
        exchange.getOut().setBody(incomeMessage);
    }
}
