package ru.otus.softwaredesign.auth.domain;

import lombok.Value;

@Value
public class TokenResponse {
    private String token;
}
