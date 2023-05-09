package com.epam.serializers;

import com.epam.dtos.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Slf4j
public class EventDeserializer implements Deserializer<Event> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Event deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(new String(data, "UTF-8"), Event.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Event");
        }
    }

    @Override
    public void close() {
    }
}

