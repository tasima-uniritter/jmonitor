package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MonitorProcessorTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private MonitorProcessor monitorProcessor;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveEventShouldCallEventService() {
        Event event = new Event();
        when(eventService.save(any(Event.class))).thenReturn(event);
        monitorProcessor.saveEvent(event);

        verify(eventService, times(1)).save(event);
    }
}
