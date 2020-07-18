package ru.otus.softwaredesign.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.softwaredesign.domain.HealthResponse;

@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(
            new HealthResponse()
                .setStatus("OK")
        );
    }
}
