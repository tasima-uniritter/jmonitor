package br.edu.uniritter.monitors.route.processor;

import br.edu.uniritter.monitors.entity.Event;
import br.edu.uniritter.monitors.service.EventService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeoutProcessor {

    @Autowired
    private EventService eventService;

    public List<Event> getExpiredEvents() {
        return eventService.getExipired(1L);
    }
}
