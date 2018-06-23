package br.edu.uniritter.monitors.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationConfig {

    @Value("${amqp-protocol}")
    private String amqpProtocol;

    @Value("${amqp-host}")
    private String amqpHost;

    @Value("${amqp-port}")
    private String amqpPort;

    @Value("${amqp-username}")
    private String amqpUsername;

    private String amqpPassword;

    public String getPasswod() {
        String password = Dotenv.configure().ignoreIfMissing().directory("./").load().get("AMQP_SERVICE_PASSWORD");
        if (password == null) {
            throw new IllegalArgumentException("Missing AMQP_SERVICE_PASSWORD environment variable");
        }
        return password;
    }
}
