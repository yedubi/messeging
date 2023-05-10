package com.epam.controller;

import com.epam.dtos.Event;
import com.epam.impl.EventSearchServiceImpl;
import com.epam.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventServiceController {

    private final EventService eventService;

    private final EventSearchServiceImpl eventSearchService;

    public EventServiceController(EventService eventService, EventSearchServiceImpl eventSearchService) {
        this.eventService = eventService;
        this.eventSearchService = eventSearchService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Event event = eventService.getEvent(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Event>> getEventByName(@PathVariable String name) {
        List<Event> events = eventSearchService.getEvent(name);
        if (!events.isEmpty()) {
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}
