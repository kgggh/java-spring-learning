package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {
    private static final String TOPIC = "test-topic";

    @KafkaListener(topics = TOPIC)
    public void subscribe(String message) {

        log.info("[Consumer-1] {}",message);
    }
}
