package com.epam.impl;

import com.epam.dtos.Event;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
@RequiredArgsConstructor
public class EventSearchServiceImpl {

    private static final String PRODUCT_INDEX = "eventindex";
    private final ElasticsearchOperations elasticsearchOperations;

    public Event createEvent(Event event) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(String.valueOf(event.getId()))
                .withObject(event).build();
        String documentId = elasticsearchOperations
                .index(indexQuery, IndexCoordinates.of(PRODUCT_INDEX));
        return event;
    }

    public List<Event> getEvent(String name) {

        QueryBuilder queryBuilder1 =
                multiMatchQuery(name, "title", "place");

        QueryBuilder queryBuilder = boolQuery()
                .must(multiMatchQuery("Footbal", "title", "place", "event_type", "speaker"))
                .filter(termQuery("event_type", "workshop"));

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Event> productHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Event.class,
                                IndexCoordinates.of(PRODUCT_INDEX));

        List<Event> eventMatches = new ArrayList<Event>();
        productHits.forEach(searchHit -> {
            eventMatches.add(searchHit.getContent());
        });
        return eventMatches;

    }

}
