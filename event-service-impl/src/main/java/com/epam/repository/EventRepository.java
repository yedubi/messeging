package com.epam.repository;

import com.epam.dtos.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EventRepository
        extends ElasticsearchRepository<Event, String> {
}