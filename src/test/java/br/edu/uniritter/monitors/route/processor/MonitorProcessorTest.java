package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.entity.Monitor;
import br.edu.uniritter.monitors.service.MonitorService;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.mockito.Mockito.*;

public class MonitorProcessorTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Exchange exchange;

    @Mock
    private Message message;

    @Mock
    private MonitorService monitorService;

    @InjectMocks
    private MonitorProcessor monitorProcessor;

    private Event event;

    @Mock
    private Monitor monitor;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        event = random(Event.class);
    }

    @Test
    public void getMonitorShouldSetMonitorHeaderWhenMonitorFound() {
        // given I have an exchange
        monitor = random(Monitor.class);
        when(exchange.getIn().getBody(Event.class)).thenReturn(event);
        when(monitorService.findOneByOriginAndMetric(event.getOrigin(), event.getMetric())).thenReturn(monitor);
        when(exchange.getOut()).thenReturn(message);
        // when I call getMonitor
        monitorProcessor.getMonitor(exchange);
        // then I expect that monitor header is not null
        verify(message, times(1)).setHeader(MonitorProcessor.MONITOR_HEADER, monitor);
    }

    @Test
    public void getMonitorShouldSetMonitorHeaderToNullWhenMonitorNotFound() {
        // given I have an exchange
        when(exchange.getIn().getBody(Event.class)).thenReturn(event);
        when(exchange.getOut()).thenReturn(message);
        // when I call getMonitor
        monitorProcessor.getMonitor(exchange);
        // then I expect that monitor header is null
        verify(message, times(1)).setHeader(MonitorProcessor.MONITOR_HEADER, null);
    }


    @Test
    public void setShouldAlertShouldNotSetHeaderSetShouldAlertWhenMonitorFound() {
        // given I have an exchange
        // and monitor header is null
        when(exchange.getIn()).thenReturn(message);
        when(exchange.getOut()).thenReturn(message);
        // when I call setShouldAlert
        monitorProcessor.setShouldAlert(exchange);
        // then I expect that setShouldAlert header is null
        verify(message, times(0)).setHeader(anyString(), anyBoolean());
    }

    @Test
    public void setShouldAlertShouldNotSetHeaderSetShouldAlertWhenMonitorCompareIsFalse() {
        // given I have an exchange
        // and monitor header is null
        when(exchange.getIn().getBody(Event.class)).thenReturn(event);
        when(monitor.compare(anyLong())).thenReturn(false);
        when(exchange.getIn().getHeader(MonitorProcessor.MONITOR_HEADER, Monitor.class)).thenReturn(monitor);
        when(exchange.getOut()).thenReturn(message);
        // when I call setShouldAlert
        monitorProcessor.setShouldAlert(exchange);
        // then I expect that setShouldAlert header is null
        verify(message, times(0)).setHeader(anyString(), anyBoolean());
    }

    @Test
    public void setShouldAlertShouldSetHeaderSetShouldAlertWhenMonitorCompareIsTrue() {
        // given I have an exchange
        // and monitor header is null
        when(exchange.getIn().getBody(Event.class)).thenReturn(event);
        when(monitor.compare(anyLong())).thenReturn(true);
        when(exchange.getIn().getHeader(MonitorProcessor.MONITOR_HEADER, Monitor.class)).thenReturn(monitor);
        when(exchange.getOut()).thenReturn(message);
        // when I call setShouldAlert
        monitorProcessor.setShouldAlert(exchange);
        // then I expect that setShouldAlert header is not null
        verify(message, times(1)).setHeader(anyString(), anyBoolean());
    }
}
