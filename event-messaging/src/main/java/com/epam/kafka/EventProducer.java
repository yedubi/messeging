package com.epam.kafka;

import com.epam.dtos.Event;
import com.epam.services.EventMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("kafka")
public class EventProducer implements EventMessaging {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Override
    public void createEvent(Event event) {
        System.out.println("send message");
        kafkaTemplate.send("create-event-notification", event);
    }

    @Override
    public void updateEvent(Event event) {
        kafkaTemplate.send("update-event-notification", event);
    }

    @Override
    public void deleteEvent(Event event) {
        kafkaTemplate.send("delete-event-notification", event);
    }

}
