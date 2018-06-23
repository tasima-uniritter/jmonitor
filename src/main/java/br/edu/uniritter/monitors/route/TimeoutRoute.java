package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.entity.Metric;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Slf4j
@Component
public class TimeoutRoute extends RouteBuilder {
    @Override
    public void configure() {
        Metric incomeMessage = new Metric("PC-0", br.edu.uniritter.monitors.constant.Metric.MEMORY_USAGE, 200L, new Date().getTime());
        from("timer:timeout?period=10000")
            .process(exchange -> {
                incomeMessage.setValue((long) (new Random().nextDouble() * (200L)));
                incomeMessage.setTimestamp(new Date().getTime());
                log.debug("---->>> {}", incomeMessage.toString());
                exchange.getOut().setBody(incomeMessage.toString());
            }).to("properties:{{income.connection}}");
    }
}
