package ru.otus.softwaredesign.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.softwaredesign.auth.persistence.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService extends UserDetailsService {
    User register(User user);
    boolean isAuthenticated(HttpSession httpSession);
}
