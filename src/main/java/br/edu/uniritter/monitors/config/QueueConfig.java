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
    AMQPConnectionDetails securedAmqpConnection() throws Exception {
        String url = String.format("%s://%s:%s",
            applicationConfig.getAmqpProtocol(),
            applicationConfig.getAmqpHost(),
            applicationConfig.getAmqpPort());
        String password = Dotenv.configure().ignoreIfMissing().directory("./").load().get("AMQP_SERVICE_PASSWORD");
        if (password == null) {
            throw new Exception("Missing AMQP_SERVICE_PASSWORD environment variable");
        }
        return new AMQPConnectionDetails(url, applicationConfig.getAmqpUsername(), password);
    }

}
