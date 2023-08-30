package com.example.feign.springfeign;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8081", configuration = FeignConfig.class, name = "self")
public interface SelfClient {

    @GetMapping("/api/v1/test")
    Map<String, Object> get();
}
