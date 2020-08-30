package ru.otus.softwaredesign.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;
import ru.otus.softwaredesign.auth.exception.UserExistsException;
import ru.otus.softwaredesign.auth.persistence.dao.UserRepository;
import ru.otus.softwaredesign.auth.persistence.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SessionRepository redisSessionRepository;

    @Override
    public User register(User user) {
        User userForRegistration = new User();
        userForRegistration.setUsername(user.getUsername());
        userForRegistration.setPassword(passwordEncoder.encode(user.getPassword()));
        userForRegistration.setFirstName(user.getFirstName());
        userForRegistration.setAge(user.getAge());
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserExistsException("User already exists");
        }
        return userRepository.save(userForRegistration);
    }

    @Override
    public boolean isAuthenticated(HttpSession httpSession) {
        return redisSessionRepository.findById(httpSession.getId()) != null;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(user -> org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
