package ru.otus.softwaredesign.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.SessionRepository;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends GenericFilterBean {

    private final UserDetailsService userDetailsService;
    private final RedisOperations<String, Object> redisOperations;
    private final SessionRepository redisSessionRepository;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
        throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        //extract token from header
        final String sessionToken = httpRequest.getHeader("X-Auth-Token");
        if (null != sessionToken) {

        }

        chain.doFilter(request, response);
    }
}
