package br.edu.uniritter.monitors.config;

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
        return new AMQPConnectionDetails(url, applicationConfig.getAmqpUsername(), applicationConfig.getAmqpPassword());
    }

}
