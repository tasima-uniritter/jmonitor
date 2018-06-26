package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getExpired(Long threshold) {
        // @TODO return true expired events
        // now() - event.timestamp > threshold
        return eventRepository.findAll();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
