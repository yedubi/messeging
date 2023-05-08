package com.epam.services;

import com.epam.dtos.Event;

public interface EventMessaging {
    void createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(Long id);
}
