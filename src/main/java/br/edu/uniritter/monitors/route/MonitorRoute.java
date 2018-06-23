package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.entity.Metric;
import br.edu.uniritter.monitors.route.processor.MonitorProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class MonitorRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("properties:{{income.connection}}")
                .to("log:monitor1")
                .unmarshal().json(JsonLibrary.Jackson, Metric.class)
                .to("log:monitor2")
                .bean(MonitorProcessor.class, "getThreshold")
                .bean(MonitorProcessor.class, "setShouldAlert")
                .choice()
                    .when(simple("${header.shouldAlert} == true"))
                        .marshal().json(JsonLibrary.Jackson, true)
                        .to("log:monitor3")
                        .log("Message will send to alert queue")
                        .to("properties:{{outcome.connection}}")
                    .otherwise()
                        .log("Message will not send to alert queue")
                .end()
                .to("log:monitor4");
    }
}
