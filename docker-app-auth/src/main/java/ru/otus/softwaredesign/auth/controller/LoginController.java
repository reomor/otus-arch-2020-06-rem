package ru.otus.softwaredesign.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.softwaredesign.auth.domain.LoginResponse;
import ru.otus.softwaredesign.auth.domain.SignInResponse;
import ru.otus.softwaredesign.auth.domain.TokenResponse;
import ru.otus.softwaredesign.auth.exception.UserExistsException;
import ru.otus.softwaredesign.auth.persistence.entity.User;
import ru.otus.softwaredesign.auth.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/signin")
    public ResponseEntity<SignInResponse> signIn() {
        return new ResponseEntity<>(new SignInResponse("Please login with basic authentification credentials"), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUserName(
            ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername()
        ).map(user -> ResponseEntity.ok(new LoginResponse(httpSession.getId(), user.getId())))
            .orElseGet(() -> new ResponseEntity<>(
                new LoginResponse(httpSession.getId(), null),
                HttpStatus.NOT_ACCEPTABLE)
            );
    }

    @GetMapping("/auth")
    public ResponseEntity<LoginResponse> auth(HttpSession httpSession, HttpServletResponse response) {
        if (userService.isAuthenticated(httpSession)) {
            return userService.findByUserName(
                ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
            ).map(user -> {
                response.setHeader("X-User-Id", user.getId().toString());
                return ResponseEntity.ok(new LoginResponse(httpSession.getId(), user.getId()));
            }).orElseGet(() -> ResponseEntity.ok(new LoginResponse(httpSession.getId(), null)));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/token")
    public ResponseEntity<TokenResponse> token(HttpSession httpSession) {
        return ResponseEntity.ok(new TokenResponse(httpSession.getId()));
    }
}
