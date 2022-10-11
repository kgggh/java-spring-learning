package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final KafkaService kafkaService;

    @GetMapping("/")
    public String sendMessage() {
        EventCreateRequestDto eventCreateRequestDto = EventCreateRequestDto.create();
        kafkaService.send(eventCreateRequestDto);
        return String.format("%s, %s",LocalDateTime.now(), true);
    }
}
