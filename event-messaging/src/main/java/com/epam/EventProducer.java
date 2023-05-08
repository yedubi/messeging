package com.epam;

import com.epam.dtos.Event;
import com.epam.services.EventMessaging;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class EventProducer implements EventMessaging {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public EventProducer(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createEvent(Event event) {
        kafkaTemplate.send("create-event-request", event);
    }

    @Override
    public void updateEvent(Event event) {
        kafkaTemplate.send("update-event-request", event);
    }

    @Override
    public void deleteEvent(Long id) {
//        kafkaTemplate.send("delete-event-request", id);
    }
}
