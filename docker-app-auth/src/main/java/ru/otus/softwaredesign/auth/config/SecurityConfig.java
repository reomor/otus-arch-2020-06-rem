package ru.otus.softwaredesign.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.SessionRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final SessionRepository redisSessionRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/register", "/signin").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .logout()
            .addLogoutHandler((httpServletRequest, httpServletResponse, authentication) -> {
                redisSessionRepository.deleteById(httpServletRequest.getSession().getId());
                redisSessionRepository.deleteById(httpServletRequest.getHeader("X-Auth-Token"));
            });
    }
}
