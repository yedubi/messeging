package com.epam.rabbitmq;

import com.epam.dtos.Event;
import com.epam.services.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
public class EventConsumer {

    private final EventService eventService;

    public EventConsumer(EventService eventService) {
        this.eventService = eventService;
    }

    @RabbitListener(queues = "create-event-request")
    public void createEvent(Event event) {
        eventService.createEvent(event);
    }

    @RabbitListener(queues = "update-event-request")
    public void updateEvent(Event event) {
        eventService.updateEvent(event.getId(), event);
    }

    @RabbitListener(queues = "delete-event-request")
    public void deleteEvent(Event event) {
        eventService.deleteEvent(event.getId());
    }

}
