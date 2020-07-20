package ru.otus.softwaredesign.persistence.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "USERS")
@Accessors(chain = true)
@Schema(description = "User information")
public class User {

    @Schema(description = "Unique identifier", example = "b43f58d7-4b54-40fb-8f2d-0f363e7264bd")
    @Id
    @GeneratedValue
    private UUID id;

    @Schema(description = "First name", example = "Otus")
    @Column
    private String firstName;

    @Schema(description = "Age", example = "18")
    @Column
    private Integer age;
}
