package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.route.processor.AlertProcessor;
import br.edu.uniritter.monitors.route.processor.EventProcessor;
import br.edu.uniritter.monitors.route.processor.MonitorProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitorRoute extends RouteBuilder {
    @Autowired
    private EventProcessor eventProcessor;

    @Autowired
    private MonitorProcessor monitorProcessor;

    @Autowired
    private AlertProcessor alertProcessor;

    @Override
    public void configure() {
        from("properties:{{income.connection}}")
            .to("log:monitor1")
            .unmarshal().json(JsonLibrary.Jackson, Event.class)
            .to("log:monitor2")
            .bean(eventProcessor, "saveEvent")
            .bean(monitorProcessor, "getMonitor")
            .bean(monitorProcessor, "setShouldAlert")
            .choice()
            .when(simple("${header.shouldAlert} == true"))
                .bean(alertProcessor, "buildAlert")
                .to("log:alertFromMonitor")
                .marshal().json(JsonLibrary.Jackson, true)
                .log("Message will send to alert queue ${body}")
                .to("properties:{{outcome.connection}}")
            .otherwise()
                .log("Message will not send to alert queue")
            .end();
    }
}
