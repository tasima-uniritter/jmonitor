package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TimeoutProcessorTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private TimeoutProcessor timeoutProcessor;


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getExpiredEventsShouldCallEventService() {
        Event event;
        List<Event> events = new ArrayList<>();

        when(eventService.getExipired(any(Long.class))).thenReturn(events);

        assertEquals(events, timeoutProcessor.getExpiredEvents());

        verify(eventService, times(1)).getExipired(any(Long.class));
    }
}
