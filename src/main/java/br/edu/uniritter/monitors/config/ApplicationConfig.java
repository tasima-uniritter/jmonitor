package br.edu.uniritter.monitors.config;

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
}
