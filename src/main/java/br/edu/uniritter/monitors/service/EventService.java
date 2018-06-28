package br.edu.uniritter.monitors.service;

import br.edu.uniritter.monitors.constant.Rule;
import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    private Rule rule = Rule.TIMEOUT;

    public List<Event> getExpired(Calendar calendar, Long threshold) {
        List<Event> allEvents = eventRepository.findAll();

        return allEvents.stream().filter(event -> {
            Long value = calendar.getTimeInMillis() - event.getTimestamp();
            event.setTimestamp(calendar.getTimeInMillis());
            event.setValue(value);
            log.debug("filter expired {} {} {}", rule.compare(value, threshold), event, threshold);
            return rule.compare(value, threshold);
        }).collect(toList());
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
