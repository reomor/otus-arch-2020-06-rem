package ru.otus.softwaredesign.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {

    @GetMapping
    public ResponseEntity<String> getAll(HttpSession httpSession) {
        return ResponseEntity.ok(httpSession.getId());
    }
}
