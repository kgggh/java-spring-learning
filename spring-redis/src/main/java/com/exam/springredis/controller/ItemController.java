package com.exam.springredis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final RedisTemplate<String, String> redisTemplate;
    private static Integer SEQ = 1;

    @GetMapping("{id}")
    public ResponseEntity<Object> getCart(@PathVariable(name = "id") String id) {
        String itemName = redisTemplate.opsForValue().get(id);
        return ResponseEntity.ok(itemName);
    }

    @PostMapping()
    public ResponseEntity<Object> addItemInCart(@RequestBody String itemName) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.append(SEQ.toString(), itemName);
        SEQ++;
        return ResponseEntity.ok("");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> removeItemInCart(@PathVariable(name = "id") String id) {
        Boolean isRemove = redisTemplate.delete(id);
        return ResponseEntity.ok(isRemove);
    }
}
