package br.edu.uniritter.monitors.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    AMQPConnectionDetails securedAmqpConnection() {
        String url = String.format("%s://%s:%s",
            applicationConfig.getAmqpProtocol(),
            applicationConfig.getAmqpHost(),
            applicationConfig.getAmqpPort());
        String password = Dotenv.configure().directory("./").load().get("AMQP_SERVICE_PASSWORD");
        return new AMQPConnectionDetails(url, applicationConfig.getAmqpUsername(), password);
    }

}
