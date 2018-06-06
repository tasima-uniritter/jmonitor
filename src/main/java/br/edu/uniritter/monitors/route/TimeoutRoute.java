package br.edu.uniritter.monitors.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimeoutRoute extends RouteBuilder {
    @Override
    public void configure() {
        String testMessage = "{\"origin\":\"some-origin\",\"metric\":\"memory-usage\",\"value\":500}";
        from("timer:timeout?period=10000")
            .process(exchange -> exchange.getOut().setBody(testMessage)).to("properties:{{income.connection}}");
    }
}
