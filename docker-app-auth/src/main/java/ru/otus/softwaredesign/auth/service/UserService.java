package ru.otus.softwaredesign.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.softwaredesign.auth.persistence.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Service for operation with users and authentication
 */
public interface UserService extends UserDetailsService {

    /**
     * Register user with {@link User} information
     *
     * @param user user information
     * @return created user
     */
    User register(User user);

    /**
     * Check whether user is authenticated
     *
     * @param httpSession current session
     * @return true if authenticated, else - false
     */
    boolean isAuthenticated(HttpSession httpSession);

    /**
     * Find user by username
     *
     * @param username user login name
     * @return optional with user
     */
    Optional<User> findByUserName(String username);
}
