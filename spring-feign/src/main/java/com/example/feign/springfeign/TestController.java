package com.example.feign.springfeign;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final BoredClient boredClient;
    private final SelfClient selfClient;

    @GetMapping("/api/v1/sample")
    public ResponseEntity<Object> getActivity() {
        return ResponseEntity.ok(boredClient.get());
    }

    @GetMapping("/api/v1/test")
    public ResponseEntity<Object> get() {
        return ResponseEntity.ok(
            new HashMap<>() {
                {
                    put("activity", "Watch a movie you'd never usually watch");
                    put("type", "relaxation");
                    put("participants", "1");
                    put("price", "0.15");
                }
            });
    }

    @GetMapping("/api/v2/test")
    public ResponseEntity<Object> get2() {
        return ResponseEntity.ok(selfClient.get());
    }
}
