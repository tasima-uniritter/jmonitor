package br.edu.uniritter.monitors.config;

import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class QueueConfigTest {

    @Mock
    private ApplicationConfig applicationConfig;

    @InjectMocks
    private QueueConfig queueConfig;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAnAMQPConnectionDetails() {
        // given I have an ApplicationConfig object
        Mockito.when(applicationConfig.getAmqpHost()).thenReturn("host");
        Mockito.when(applicationConfig.getAmqpPort()).thenReturn("port");
        Mockito.when(applicationConfig.getAmqpProtocol()).thenReturn("protocol");
        Mockito.when(applicationConfig.getAmqpUsername()).thenReturn("username");
        Mockito.when(applicationConfig.getAmqpPassword()).thenReturn("passord");

        // when I call securedAmqpConnection
        AMQPConnectionDetails amqpConnectionDetails = queueConfig.securedAmqpConnection();

        // then I have an AMQPConnectionDetails object
        assertThat(amqpConnectionDetails).isInstanceOf(AMQPConnectionDetails.class);
    }
}
