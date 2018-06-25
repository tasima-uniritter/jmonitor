package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.constant.MetricName;
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
        Metric metric = new Metric("PC-0", MetricName.MEMORY_USAGE, 200L, new Date().getTime());
        from("timer:timeout?period=10000")
            .process(exchange -> {
                metric.setValue((long) (new Random().nextDouble() * (200L)));
                metric.setTimestamp(new Date().getTime());
                log.debug("---->>> {}", metric);
                exchange.getOut().setBody(metric, String.class);
            }).to("properties:{{income.connection}}");
    }
}
