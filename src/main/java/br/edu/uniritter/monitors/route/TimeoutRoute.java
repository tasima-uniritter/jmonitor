package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.entity.Event;
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
        Event event = new Event("PC-0", Metric.MEMORY_USAGE, 200L, new Date().getTime());
        from("timer:timeout?period=10000")
            .process(exchange -> {
                event.setValue((long) (new Random().nextDouble() * (200L)));
                event.setTimestamp(new Date().getTime());
                log.debug("---->>> {}", event);
                exchange.getOut().setBody(event, String.class);
            }).to("properties:{{income.connection}}");
    }
}
