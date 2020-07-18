package ru.otus.softwaredesign.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HealthResponse {
    private String status;
}
