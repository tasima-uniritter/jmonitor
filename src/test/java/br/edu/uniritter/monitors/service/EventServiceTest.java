package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getExpiredShouldCallEventsRepositoryFindAll() {
        // given the mocked events
        Long threshold = 30000L;
        Calendar calendar = Calendar.getInstance();
        Event unexpired = new Event();
        unexpired.setTimestamp(calendar.getTimeInMillis());
        Event expired = new Event();
        expired.setTimestamp(calendar.getTimeInMillis() - threshold - 1);
        List<Event> events = new ArrayList<Event>() {{
            add(unexpired);
            add(expired);
        }};
        List<Event> expected = new ArrayList<Event>() {{
            add(expired);
        }};
        when(eventRepository.findAll()).thenReturn(events);

        // when I call service getExpired
        List<Event> returned = eventService.getExpired(calendar, threshold);

        // then I expect expired events
        assertEquals(expected, returned);

    }

    @Test
    public void saveShouldCallEventsRepositorySave() {
        // given the mocked event
        Event expected = new Event();

        // when I call service save
        when(eventRepository.save(expected)).thenReturn(expected);

        Event event = eventService.save(expected);

        // then I expect all monitors
        assertSame(expected, event);

    }
}
