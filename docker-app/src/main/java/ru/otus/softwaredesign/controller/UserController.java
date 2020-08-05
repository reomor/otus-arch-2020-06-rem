package ru.otus.softwaredesign.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.softwaredesign.exception.BaseDockerAppException;
import ru.otus.softwaredesign.persistence.dao.UserRepository;
import ru.otus.softwaredesign.persistence.entity.User;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(BaseDockerAppException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User post(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User put(@PathVariable UUID userId, @RequestBody User user) {
        return userRepository.findById(userId)
            .map(updatedUser -> {
                updatedUser.setFirstName(user.getFirstName());
                updatedUser.setAge(user.getAge());
                return userRepository.save(updatedUser);
            }).orElseThrow(BaseDockerAppException::new);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID userId) {
        userRepository.deleteById(userId);
    }
}
