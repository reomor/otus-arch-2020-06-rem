package ru.otus.softwaredesign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.softwaredesign.domain.AppResponse;
import ru.otus.softwaredesign.domain.HealthResponse;
import ru.otus.softwaredesign.domain.VersionResponse;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RestController
public class AppController {

    @GetMapping("/")
    public ResponseEntity<AppResponse> get() {
        return ResponseEntity.ok(
            new AppResponse("Hello from " + getHostName())
        );
    }

    @GetMapping("/version")
    public ResponseEntity<VersionResponse> version() {
        return ResponseEntity.ok(
            new VersionResponse("0.2")
        );
    }

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(
            new HealthResponse()
                .setStatus("OK")
        );
    }

    private String getHostName() {
        try {
            InetAddress myHost = InetAddress.getLocalHost();
            return myHost.getHostName();
        } catch (UnknownHostException e) {
            log.error("", e);
            return "unknown";
        }
    }
}
