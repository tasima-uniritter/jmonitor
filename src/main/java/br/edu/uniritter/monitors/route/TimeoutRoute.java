package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.config.ApplicationConfig;
import br.edu.uniritter.monitors.route.processor.AlertProcessor;
import br.edu.uniritter.monitors.route.processor.MonitorProcessor;
import br.edu.uniritter.monitors.route.processor.TimeoutProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class TimeoutRoute extends RouteBuilder {
    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private MonitorProcessor monitorProcessor;

    @Autowired
    private TimeoutProcessor timeoutProcessor;

    @Autowired
    private AlertProcessor alertProcessor;

    @Override
    public void configure() {
        from("timer:timeout?period=10000")
            .process(exchange -> exchange.getOut().setBody(
                timeoutProcessor.getExpiredEvents(Calendar.getInstance(), applicationConfig.getDefaultThreshold())
            ))
            .to("log:eventsExpired")
            .split(body())
            .bean(monitorProcessor, "getMonitor")
            .to("log:eventsExpiredWithMonitor")
            .choice()
            .when(simple("${header.monitor} != null"))
                .bean(alertProcessor, "buildTimeoutAlert")
                .to("log:alertFromTimeout")
                .marshal().json(JsonLibrary.Jackson, true)
                .log(LoggingLevel.INFO, log.getName(), "Message will send to alert queue ${body}")
                .to("properties:{{outcome.connection}}")
            .otherwise()
                .log(LoggingLevel.INFO, log.getName(),"Message will not send to alert queue")
        ;
    }
}
