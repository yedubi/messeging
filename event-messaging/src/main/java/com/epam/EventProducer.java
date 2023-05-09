package com.epam;

import com.epam.dtos.Event;
import com.epam.services.EventMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Profile("kafka")
public class EventProducer implements EventMessaging {

    private final KafkaTemplate<String, Event> kafkaTemplate;

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
