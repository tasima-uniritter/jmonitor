package br.edu.uniritter.monitors.route;

import br.edu.uniritter.monitors.entity.IncomeMessage;
import br.edu.uniritter.monitors.route.processor.MonitorProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitorRoute extends RouteBuilder {

    @Autowired
    private MonitorProcessor monitorProcessor;

    @Override
    public void configure() {
        from("rabbitmq://user:pass@localhost/test?prefetchCount=1&threadPoolSize=1&exchangeType=fanout&skipExchangeDeclare=true")
                .to("log:monitor1")
                .unmarshal().json(JsonLibrary.Gson, IncomeMessage.class)
                .to("log:monitor2")
                .process(monitorProcessor)
                .choice()
                    .when(simple("${header.shouldAlert} == true"))
                        .marshal().json(JsonLibrary.Gson, true)
                        .to("log:monitor3")
                        .log("Message will send to alert queue")
                        .to("rabbitmq://user:passs@localhost/testout?threadPoolSize=1&exchangeType=fanout&skipExchangeDeclare=true&skipQueueDeclare=true")
                    .otherwise()
                        .log("Message will not send to alert queue")
                .end()
                .to("log:monitor4");
    }
}
