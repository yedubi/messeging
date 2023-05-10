package com.epam.rabbitmq;

import com.epam.dtos.Event;
import com.epam.services.EventMessaging;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
public class EventProducer implements EventMessaging {

    private final RabbitTemplate rabbitTemplate;

    public EventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void createEvent(Event event) {
        rabbitTemplate.convertAndSend("create-event-notification", event);
    }

    @Override
    public void updateEvent(Event event) {
        rabbitTemplate.convertAndSend("update-event-notification", event);
    }

    @Override
    public void deleteEvent(Event event) {
        rabbitTemplate.convertAndSend("delete-event-notification", event);
    }
}
