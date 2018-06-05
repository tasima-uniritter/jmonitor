package br.edu.uniritter.monitors.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimeoutRoute extends RouteBuilder {
    @Override
    public void configure() {
        String testMessage = "{\"origin\":\"some-origin\",\"metric\":\"some-metric\",\"value\":500.0}";
        from("timer:timeout?period=10000")
            .process(new Processor() {
                public void process(Exchange exchange) {
                    exchange.getOut().setBody(testMessage);
                }
            }).to("properties:{{income.connection}}");
    }
}
