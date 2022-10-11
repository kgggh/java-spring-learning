package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateRequestDto {
    private String eventId;
    private String type;
    private String description;

    public static EventCreateRequestDto create() {
        return new EventCreateRequestDto(UUID.randomUUID().toString(), "Q1", LocalDateTime.now().toString());
    }
}
