package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventProcessor {

    @Autowired
    private EventService eventService;

    public void saveEvent(Event event) {
        log.debug("Event saved ---->>> {}", eventService.save(event));
    }

}
