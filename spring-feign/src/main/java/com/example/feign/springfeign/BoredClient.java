package com.example.feign.springfeign;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://www.boredapi.com", configuration = FeignConfig.class, name = "bored")
public interface BoredClient {

    @GetMapping("/api/activity")
    Map<String, Object> get();

}
