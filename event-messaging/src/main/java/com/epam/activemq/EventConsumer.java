package com.epam.activemq;

import com.epam.dtos.Event;
import com.epam.services.EventService;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("activemq")
public class EventConsumer {

    private final EventService eventService;

    public EventConsumer(EventService eventService) {
        this.eventService = eventService;
    }

    @JmsListener(destination = "create-event-request")
    public void createEvent(Event event) {
        eventService.createEvent(event);
    }

    @JmsListener(destination = "update-event-request")
    public void updateEvent(Event event) {
        eventService.updateEvent(event.getId(), event);
    }

    @JmsListener(destination = "delete-event-request")
    public void deleteEvent(Event event) {
        eventService.deleteEvent(event.getId());
    }

}
