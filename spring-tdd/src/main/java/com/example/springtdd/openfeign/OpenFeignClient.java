package com.example.springtdd.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "enties", url = "${open-api.base.url}")
public interface OpenFeignClient {

    @GetMapping(path = "/random")
    Map<String, Object> getRandomValue();
}
