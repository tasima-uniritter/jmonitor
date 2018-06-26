package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
    public void getExipiredShouldCallEventsRepositoryFindAll() {
        // given the mocked events
        List<Event> expected = new ArrayList<>();
        expected.add(new Event());

        // when I call service All
        when(eventRepository.findAll()).thenReturn(expected);

        List<Event> events = eventService.getExpired(1L);

        // then I expect all monitors
        assertSame(expected, events);

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
