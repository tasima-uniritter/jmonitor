package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;

import java.util.Calendar;
import java.util.Random;

@Slf4j
public class TimerRoute extends RouteBuilder {

    @Override
    public void configure() {
        Event event = new Event();
        event.setMetric(Metric.MEMORY_USAGE);

        from("timer:timeout?period=5000")
            .process(exchange -> {
                event.setOrigin("PC-" + (new Random().nextInt(2)));
                event.setValue((long) (new Random().nextDouble() * (200L)));
                event.setTimestamp(Calendar.getInstance().getTimeInMillis());
                log.debug("---->>> {}", event);
                exchange.getOut().setBody(event.toString());
            }).to("properties:{{income.connection}}")
        ;
    }
}
