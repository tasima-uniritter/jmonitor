package br.edu.uniritter.monitors.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationConfig {

    private Dotenv dotenv = Dotenv.configure().ignoreIfMissing().directory("./").load();

    @Value("${amqp-protocol}")
    private String amqpProtocol;

    @Value("${amqp-host}")
    private String amqpHost;

    @Value("${amqp-port}")
    private String amqpPort;

    @Value("${amqp-username}")
    private String amqpUsername;

    @Value("${default-threshold}")
    private Long defaultThreshold;

    public String getAmqpPassword() {
        String amqpPassword = dotenv.get("AMQP_SERVICE_PASSWORD");
        if (amqpPassword == null) {
            throw new IllegalArgumentException("Missing AMQP_SERVICE_PASSWORD environment variable");
        }
        return amqpPassword;
    }
}
