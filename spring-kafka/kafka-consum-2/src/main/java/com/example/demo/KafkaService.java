package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {
    private final ObjectMapper objectMapper;

    private final String TOPIC = "test-topic";

    @KafkaListener(topics = TOPIC)
    public void subscribe(String message) {
        log.info("[Consumer-2] {}", message);

        /* Json to DTO */
        EventCreateResponseDto eventCreateResponseDto = null;
        try {
            log.warn("JsonObject to DTO ");
            eventCreateResponseDto = objectMapper.readValue(message, EventCreateResponseDto.class);
            log.info(eventCreateResponseDto.toString());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

}
