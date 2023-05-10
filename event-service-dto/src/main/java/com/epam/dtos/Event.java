package com.epam.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Document(indexName = "eventindex")
@NoArgsConstructor
public class Event {
    @Id
    private long id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "place")
    private String place;

    @Field(type = FieldType.Text, name = "speaker")
    private String speaker;

    @Field(type = FieldType.Text, name = "event_type")
    private EventType eventType;

//    @Field(type = FieldType.Date, name = "date_time")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate dateTime;

    public enum EventType {
        WORKSHOP,
        TECH_TALK
    }

}
