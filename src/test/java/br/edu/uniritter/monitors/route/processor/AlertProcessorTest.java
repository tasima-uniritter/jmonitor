package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.config.ApplicationConfig;
import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.entity.Alert;
import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.entity.Monitor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@Component
public class AlertProcessorTest {

    @Mock
    private ApplicationConfig applicationConfig;

    @InjectMocks
    private AlertProcessor alertProcessor;

    private Event event;
    private Monitor monitor;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        event = random(Event.class);
        monitor = random(Monitor.class);
    }

    @Test
    public void buildAlertShouldSetMonitorAndEvent() {
        // given I have an event
        // and I have a monitor
        // when I call buildAlert
        Alert alert = alertProcessor.buildAlert(monitor, event);
        // then the alert should have all fields filed
        Alert expected = new Alert() {{
            setOrigin(event.getOrigin());
            setMetric(event.getMetric());
            setValue(event.getValue());
            setTimestamp(event.getTimestamp());
            setRule(monitor.getRule());
            setThreshold(monitor.getThreshold());
        }};
        assertEquals(expected, alert);
    }

    @Test
    public void buildTimeoutAlertShouldSetMonitorEventAndDefaultThreshold() {
        // given I have an event
        // and I have a monitor
        // and I mock applicationConfig
        when(applicationConfig.getDefaultThreshold()).thenReturn(10L);
        // when I call buildTimeoutAlert
        Alert alert = alertProcessor.buildTimeoutAlert(monitor, event);
        // then the alert should have all fields filed
        verify(applicationConfig, times(1)).getDefaultThreshold();

        Alert expected = new Alert() {{
            setOrigin(event.getOrigin());
            setMetric(event.getMetric());
            setValue(event.getValue());
            setTimestamp(event.getTimestamp());
            setRule(Rule.TIMEOUT);
            setThreshold(applicationConfig.getDefaultThreshold());
        }};
        assertEquals(expected, alert);
    }
}
