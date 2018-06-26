package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.constant.Metric;
import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.route.processor.AlertProcessor;
import br.edu.uniritter.monitors.route.processor.MonitorProcessor;
import br.edu.uniritter.monitors.route.processor.TimeoutProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Slf4j
@Component
public class TimeoutRoute extends RouteBuilder {
    @Autowired
    private MonitorProcessor monitorProcessor;

    @Autowired
    private AlertProcessor alertProcessor;

    @Override
    public void configure() {
        Event event = new Event();
        event.setMetric(Metric.MEMORY_USAGE);
        from("timer:timeout?period=10000")
            .bean(TimeoutProcessor.class, "getExpiredEvents")
            .to("log:eventsExpired")
            .split(body())
            .bean(monitorProcessor, "getMonitor")
            .to("log:eventsExpiredWithMonitor")
            .choice()
            .when(simple("${header.getMonitor} != null"))
                .bean(alertProcessor, "buildTimeoutAlert")
                .to("log:alertFromTimeout")
                .marshal().json(JsonLibrary.Jackson, true)
                .log("Message will send to alert queue ${body}")
                .to("properties:{{outcome.connection}}")
            .otherwise()
                .log("Message will not send to alert queue (monitor is null)")
        ;

        from("timer:timeout?period=5000")
            .process(exchange -> {
                event.setOrigin("PC-" + (new Random().nextInt(2)));
                event.setValue((long) (new Random().nextDouble() * (200L)));
                event.setTimestamp(new Date().getTime());
                log.debug("---->>> {}", event);
                exchange.getOut().setBody(event.toString());
            }).to("properties:{{income.connection}}")
        ;
    }
}
