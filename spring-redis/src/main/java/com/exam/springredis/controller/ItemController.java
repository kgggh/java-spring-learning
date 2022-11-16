package com.exam.springredis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {
    private final StringRedisTemplate redisTemplate;
    private static final String KEY_PATTERN = "item:id:%s";

    @GetMapping("{id}")
    public ResponseEntity<Object> getItemId(@PathVariable(name = "id") String id) {
        String itemName = redisTemplate.opsForValue().get(makeRedisKey(id));
        return ResponseEntity.ok(itemName);
    }

    @GetMapping()
    public ResponseEntity<Object> getKeys() {
        Set<String> keys = redisTemplate.keys(makeRedisScanKey());
        return ResponseEntity.ok(List.of(keys));
    }

    @GetMapping("/scan")
    public ResponseEntity<Object> scan() {
        List<String> itemIds = new ArrayList<>();
        try(RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection()) {
            ScanOptions options = ScanOptions.scanOptions().match(makeRedisScanKey()).count(10).build();
            Cursor<byte[]> cursor = redisConnection.scan(options);
            while (cursor.hasNext()) {
                String key = new String(cursor.next());
                itemIds.add(redisTemplate.opsForValue().get(key));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(itemIds);
    }

    private String makeRedisScanKey() {
        return String.format(KEY_PATTERN, "*");
    }

    private String makeRedisKey(String id) {
        return String.format(KEY_PATTERN, id);
    }

    @PostMapping("{id}")
    public ResponseEntity<Object> addItemId(@PathVariable(name = "id") String itemId) {
        log.info(String.format("[input] %s", itemId));
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String makeKey = String.format(KEY_PATTERN, itemId);
        ops.increment(makeKey);
        return ResponseEntity.ok("success");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> modifyItemId(@PathVariable(name = "id") String itemId) {
        log.info(String.format("[input] %s", itemId));
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String makeKey = String.format(KEY_PATTERN, itemId);
        ops.set(makeKey, "수정합니다.");
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> removeItemById(@PathVariable(name = "id") String id) {
        Boolean isRemove = redisTemplate.delete(makeRedisKey(id));
        return ResponseEntity.ok(String.format("remove key - %s, succeed - %s", id, isRemove));
    }

    @DeleteMapping()
    public ResponseEntity<Object> clear() {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushAll();
            return null;
        });
        return ResponseEntity.ok("All key clear~~~~~~~~");
    }
}
