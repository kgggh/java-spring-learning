package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "test-topic";
    private final ObjectMapper objectMapper;


    public void send(EventCreateRequestDto eventCreateRequestDto) {
        log.info("origin: " + eventCreateRequestDto);
        String message = null;

        try {
            message = objectMapper.writeValueAsString(eventCreateRequestDto);
            log.info(message);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        ListenableFuture<SendResult<String, String>> listen = kafkaTemplate.send(TOPIC, message);
        listen.addCallback(success -> {
            log.info("send topic: {}, data: {}", success.getRecordMetadata().topic(), success.getProducerRecord().value());
        }, fail -> {
            log.error("send fail: {}", fail.getMessage());
        });
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic(TOPIC, 10, (short) 3);
    }
}
