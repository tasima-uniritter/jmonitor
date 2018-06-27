package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        Event event = new Event();
        List<Event> events = new ArrayList<Event>() {{
            add(event);
        }};
        Calendar calendar = Calendar.getInstance();

        when(eventService.getExpired(any(Calendar.class), anyLong())).thenReturn(events);

        List<Event> expired = timeoutProcessor.getExpiredEvents(calendar, 1L);

        assertEquals(events, expired);

        verify(eventService, times(1)).getExpired(eq(calendar), anyLong());
    }
}
