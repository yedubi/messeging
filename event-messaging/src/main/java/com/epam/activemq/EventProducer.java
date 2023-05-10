package com.epam.activemq;

import com.epam.dtos.Event;
import com.epam.services.EventMessaging;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("activemq")
public class EventProducer implements EventMessaging {

    private final JmsTemplate jmsTemplate;

    public EventProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public void createEvent(Event event) {
        jmsTemplate.convertAndSend("create-event-notification", event);

    }

    @Override
    public void updateEvent(Event event) {
        jmsTemplate.convertAndSend("update-event-notification", event);

    }

    @Override
    public void deleteEvent(Event event) {
        jmsTemplate.convertAndSend("delete-event-notification", event);

    }
}