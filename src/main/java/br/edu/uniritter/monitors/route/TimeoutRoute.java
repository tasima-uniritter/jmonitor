package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.route.processor.TimeoutProcessor;
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
        Event event = new Event();
        event.setMetric(Metric.MEMORY_USAGE);
        from("timer:timeout?period=10000")
            .process(exchange -> {
                event.setOrigin("PC-" + (new Random().nextInt(2)));
                event.setValue((long) (new Random().nextDouble() * (200L)));
                event.setTimestamp(new Date().getTime());
                log.debug("---->>> {}", event);
                exchange.getOut().setBody(event.toString());
            }).to("properties:{{income.connection}}")

            .bean(TimeoutProcessor.class, "getExpiredEvents")
            .split(body()).to("log:eventsExpired")
        ;
    }
}
