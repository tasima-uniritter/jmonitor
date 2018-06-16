package br.edu.uniritter.monitors.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {

    private static final String AMQP_SERVICE_HOST = "b-79e24b8a-cd93-48a3-b234-9d75e05e3def-1.mq.us-east-1.amazonaws.com";
    private static final String AMQP_SERVICE_PORT = "5671";
    private static final String AMQP_SERVICE_USERNAME = "tda-monitors-a";

    @Bean
    AMQPConnectionDetails securedAmqpConnection() {
        String url = String.format("amqps://%s:%s", AMQP_SERVICE_HOST, AMQP_SERVICE_PORT);
        String password = Dotenv.configure().directory("./").load().get("AMQP_SERVICE_PASSWORD");
        return new AMQPConnectionDetails(url, AMQP_SERVICE_USERNAME, password);
    }

}
