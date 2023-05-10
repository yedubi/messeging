package com.epam.impl;

import com.epam.dtos.Event;
import com.epam.repository.EventRepository;
import com.epam.services.EventMessaging;
import com.epam.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMessaging eventMessaging;

    @Override
    public Event createEvent(Event event) {
        eventMessaging.createEvent(event);
        return eventRepository.save(event);
    }

    @SneakyThrows
    @Override
    public Event updateEvent(Long id, Event event) {
        eventMessaging.updateEvent(event);
        Optional<Event> existingEvent = eventRepository.findById(String.valueOf(id));
        if (existingEvent.isPresent()) {
            Event updatedEvent = existingEvent.get();
            updatedEvent.setTitle(event.getTitle());
            updatedEvent.setPlace(event.getPlace());
            updatedEvent.setSpeaker(event.getSpeaker());
            updatedEvent.setEventType(event.getEventType());
            updatedEvent.setDateTime(event.getDateTime());
            return eventRepository.save(updatedEvent);
        } else {
            throw new EventNotFoundException();
        }
    }

    @SneakyThrows
    @Override
    public Event getEvent(Long id) {
        Optional<Event> event = eventRepository.findById(String.valueOf(id));
        return event.orElseThrow(EventNotFoundException::new);
    }

    @SneakyThrows
    @Override
    public void deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(String.valueOf(id));
        if (event.isPresent()) {
            eventMessaging.deleteEvent(event.get());
            eventRepository.deleteById(String.valueOf(id));
        } else {
            throw new EventNotFoundException();
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return
                StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
    }
}
