package br.edu.uniritter.monitors.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimeoutRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("timer:timeout?period=10000")
                .to("log:timeout");
    }
}