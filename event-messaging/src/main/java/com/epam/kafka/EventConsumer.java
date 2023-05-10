package com.epam.kafka;

import com.epam.dtos.Event;
import com.epam.services.EventService;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("kafka")
public class EventConsumer {
    private final EventService eventService;

    public EventConsumer(EventService eventService) {
        this.eventService = eventService;
    }

    @KafkaListener(topics = "${spring.kafka.create-event-request}", groupId = "${spring.kafka.group}")
    public void createEvent(List<Event> events) {
        for (Event event : events) {
            eventService.createEvent(event);
        }
    }

    @KafkaListener(topics = "${spring.kafka.update-event-request}", groupId = "${spring.kafka.group}")
    public void updateEvent(List<Event> events) {
        for (Event event : events) {
            eventService.updateEvent(event.getId(), event);
        }
    }

    @KafkaListener(topics = "${spring.kafka.delete-event-request}", groupId = "${spring.kafka.group}")
    public void deleteEvent(List<Long> eventIds) {
        for (Long eventId : eventIds) {
            eventService.deleteEvent(eventId);
        }
    }
}
