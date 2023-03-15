package com.example.springdatakeyvalue;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.util.ConcurrentReferenceHashMap;

@Configuration
@EnableMapRepositories(
        basePackages = "com.example.springdatakeyvalue",
        mapType = ConcurrentReferenceHashMap.class
)
public class KeyValueConfig {
}
