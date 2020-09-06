package ru.otus.softwaredesign.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.softwaredesign.exception.BaseDockerAppException;
import ru.otus.softwaredesign.persistence.dao.UserRepository;
import ru.otus.softwaredesign.persistence.entity.User;

import java.util.UUID;

@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {

    private static final String USER_ID_HEADER = "X-User-Id";

    private final UserRepository userRepository;

    @GetMapping
    public User getById(@RequestHeader(USER_ID_HEADER) UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(BaseDockerAppException::new);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User put(@RequestHeader(USER_ID_HEADER) UUID userId, @RequestBody User user) {
        return userRepository.findById(userId)
            .map(updatedUser -> {
                updatedUser.setFirstName(user.getFirstName());
                updatedUser.setAge(user.getAge());
                return userRepository.save(updatedUser);
            }).orElseThrow(BaseDockerAppException::new);
    }

    @DeleteMapping
    public void delete(@RequestHeader(USER_ID_HEADER) UUID userId) {
        userRepository.deleteById(userId);
    }
}
