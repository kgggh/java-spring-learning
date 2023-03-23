package com.example.springmodulith.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerManagement customerManagement;

    @GetMapping("/")
    public ResponseEntity<Object> test(Customer customer) {
        customerManagement.signUp(customer);
        return ResponseEntity.ok("success");
    }
}
