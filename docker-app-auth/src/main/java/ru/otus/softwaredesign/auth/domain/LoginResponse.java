package ru.otus.softwaredesign.auth.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class LoginResponse {
    private String token;
    private UUID userId;
}
