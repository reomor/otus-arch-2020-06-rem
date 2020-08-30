package ru.otus.softwaredesign.auth.domain;

import lombok.Value;

@Value
public class LoginResponse {
    private String token;
}
